package com.favouritedragon.arcaneessentials.common.spell.earth;

import com.favouritedragon.arcaneessentials.ArcaneEssentials;
import com.favouritedragon.arcaneessentials.common.entity.EntitySolarBeam;
import electroblob.wizardry.constants.Element;
import electroblob.wizardry.constants.SpellType;
import electroblob.wizardry.constants.Tier;
import electroblob.wizardry.registry.WizardryItems;
import electroblob.wizardry.spell.Spell;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SolarBeam extends Spell {

	public SolarBeam() {
		super(Tier.ADVANCED, 40, Element.EARTH, "solar_beam", SpellType.ATTACK,140, EnumAction.BOW, false, ArcaneEssentials.MODID);
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellModifiers modifiers) {
		Vec3d look = caster.getLookVec();
		look = look.scale(1.15).add(caster.getPositionVector());
		if (world.getBlockState(new BlockPos(look.x, look.y, look.z)).getBlock() == Blocks.AIR) {
			double damageMult = 1.0 * modifiers.get(WizardryItems.blast_upgrade);
			float range = 20 + 5 * modifiers.get(WizardryItems.range_upgrade);
			double size = 2.0 + 1.0 * modifiers.get(WizardryItems.blast_upgrade);
			EntitySolarBeam beam = new EntitySolarBeam(world, look.x, look.y, look.z, caster, 100, (float) damageMult);
			beam.setRadius((float) size / 2);
			beam.setRange(range);
			beam.rotationPitch = caster.rotationPitch;
			beam.rotationYaw = caster.rotationYaw;
			beam.motionX = beam.motionY = beam.motionZ = 0;
			world.spawnEntity(beam);
			caster.swingArm(hand);
			return true;
		}
		return false;
	}

	@Override
	public boolean cast(World world, EntityLiving caster, EnumHand hand, int ticksInUse, EntityLivingBase target, SpellModifiers modifiers) {
		Vec3d look = caster.getLookVec();
		look = look.scale(1.15).add(caster.getPositionVector());
		if (world.getBlockState(new BlockPos(look.x, look.y, look.z)).getBlock() == Blocks.AIR) {
			double damageMult = 1.0 * modifiers.get(WizardryItems.blast_upgrade);
			float range = 20 + 5 * modifiers.get(WizardryItems.range_upgrade);
			double size = 2.0 + 1.0 * modifiers.get(WizardryItems.blast_upgrade);
			EntitySolarBeam beam = new EntitySolarBeam(world, look.x, look.y, look.z, caster, 100, (float) damageMult);
			beam.setRadius((float) size / 2);
			beam.setRange(range);
			beam.rotationPitch = caster.rotationPitch;
			beam.rotationYaw = caster.rotationYaw;
			beam.motionX = beam.motionY = beam.motionZ = 0;
			world.spawnEntity(beam);
			caster.swingArm(hand);
			return true;
		}
		return super.cast(world, caster, hand, ticksInUse, target, modifiers);
	}

	@Override
	public boolean canBeCastByNPCs() {
		return true;
	}


}
