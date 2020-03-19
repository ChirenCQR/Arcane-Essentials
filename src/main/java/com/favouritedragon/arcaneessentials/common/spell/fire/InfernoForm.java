package com.favouritedragon.arcaneessentials.common.spell.fire;

import com.favouritedragon.arcaneessentials.common.potion.ArcanePotions;
import com.favouritedragon.arcaneessentials.common.spell.ArcaneSpell;
import electroblob.wizardry.registry.WizardrySounds;
import electroblob.wizardry.util.ParticleBuilder;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class InfernoForm extends ArcaneSpell {

	public InfernoForm() {
		super("inferno_form", EnumAction.BOW, false);
		addProperties(BURN_DURATION, DAMAGE, EFFECT_RADIUS, EFFECT_DURATION);
		soundValues(2.0F, 0.7F, 0.15F);
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellModifiers modifiers) {
		if (world.isRemote) {
			ParticleBuilder.create(ParticleBuilder.Type.BUFF).entity(caster).time(10).clr(252, 25, 25).spawn(world);
		}
		int duration = getProperty(EFFECT_DURATION).intValue();
		caster.addPotionEffect(new PotionEffect(ArcanePotions.infernoForm, duration, 0, false, false));
		caster.playSound(WizardrySounds.ENTITY_FIREBOMB_FIRE, 1.0F + world.rand.nextFloat() / 10, 0.8F + world.rand.nextFloat() / 10);
		caster.swingArm(hand);
		playSound(world, caster, ticksInUse, -1, modifiers);
		return true;
	}
}
