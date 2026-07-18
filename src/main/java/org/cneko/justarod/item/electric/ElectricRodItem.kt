package org.cneko.justarod.item.electric

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.world.World
import org.cneko.justarod.item.rod.EndRodInstructions
import org.cneko.justarod.item.rod.EndRodItem
import org.cneko.justarod.item.rod.SelfUsedItemInterface

/*
电动的就不需要自己动手啦... 不那么费力的说
但是振动的话就是越快越爽呢
 */
abstract class ElectricRodItem(settings: Settings) : EndRodItem(settings) {
    open fun getEnergyCapacity(stack: ItemStack?): Long {
        return stack?.maxDamage?.toLong() ?: 0L
    }

    open fun getEnergyMaxInput(stack: ItemStack?): Long {
        return 1000
    }

    open fun getEnergyMaxOutput(stack: ItemStack?): Long {
        return 1000
    }

    fun getStoredEnergy(stack: ItemStack): Long {
        return stack.nbt?.getLong(ENERGY_KEY) ?: 0L
    }

    fun setStoredEnergy(stack: ItemStack, energy: Long) {
        val safeEnergy = energy.coerceIn(0L, getEnergyCapacity(stack))
        if (safeEnergy == 0L) {
            stack.nbt?.remove(ENERGY_KEY)
        } else {
            stack.orCreateNbt.putLong(ENERGY_KEY, safeEnergy)
        }
    }

    fun tryUseEnergy(stack: ItemStack, amount: Long): Boolean {
        if (amount < 0L || stack.count != 1) return false
        val remaining = getStoredEnergy(stack) - amount
        if (remaining < 0L) return false
        setStoredEnergy(stack, remaining)
        return true
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        val energy = getStoredEnergy(stack)
        val showEnergy: String = if (energy in 1000..1000000){
            "${energy / 1000}k"
        }else if (energy >= 1000000){
            "${energy / 1000000}M"
        }else{
            "$energy"
        }
        val maxEnergy = getEnergyCapacity(stack)
        val maxShowEnergy: String = if (maxEnergy in 1000..1000000){
            "${maxEnergy / 1000}k"
        }else if (maxEnergy >= 1000000){
            "${maxEnergy / 1000000}M"
        }else{
            "$maxEnergy"
        }
        tooltip.add(Text.translatable("item.justarod.electric_rod.tooltip", showEnergy, maxShowEnergy).formatted(Formatting.GOLD))
    }

    override fun onCraft(stack: ItemStack, world: World, player: PlayerEntity) {
        super.onCraft(stack, world, player)
        stack.damage = stack.maxDamage
    }

    override fun damage(stack: ItemStack, amount: Int, world: World?) {
        super.damage(stack, amount, world)
        this.setStoredEnergy(stack, (stack.maxDamage - stack.damage).toLong())
    }

    override fun canDamage(stack: ItemStack, amount: Int): Boolean {
        return this.getStoredEnergy(stack)>this.getEnergyCapacity(stack)*0.01 && this.getStoredEnergy(stack) >= amount
    }

    override fun inventoryTick(
        stack: ItemStack?,
        world: World?,
        entity: net.minecraft.entity.Entity?,
        slot: Int,
        selected: Boolean
    ) {
        // 设置耐久与能量同步
        if (stack == null) return
        val stored = getStoredEnergy(stack).coerceIn(0L, stack.maxDamage.toLong())
        stack.damage = stack.maxDamage - stored.toInt()
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    companion object {
        private const val ENERGY_KEY = "energy"
    }
}

abstract class SelfUsedElectricRodItem(settings: Settings) : ElectricRodItem(settings), SelfUsedItemInterface {

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        val speed = this.getRodSpeed(stack)
        tooltip.add(Text.translatable("item.justarod.end_rod.speed", speed).formatted(Formatting.LIGHT_PURPLE))
    }
    override fun inventoryTick(
        stack: ItemStack?,
        world: World?,
        entity: net.minecraft.entity.Entity?,
        slot: Int,
        selected: Boolean
    ) {
        super.inventoryTick(stack, world, entity, slot, selected)

        // 如果耐久为0或者实体不是LivingEntity，则不处理
        if(stack!!.damage == stack.maxDamage || entity !is LivingEntity) return

        val e: LivingEntity = entity

        // 如果放在副手
        if (
            e.getStackInHand(Hand.OFF_HAND) == stack //是的,直接用==
            || slot == Int.MIN_VALUE // now works with inserted rods
        ){
            // 减少一点耐久 (即使没耐久也不损坏)
            stack.damage++
            // 执行
            useOnSelf(stack, world, e, slot, selected)
        }
    }
    override fun getInstruction(): EndRodInstructions {
        return EndRodInstructions.USE_ON_SELF
    }
}
