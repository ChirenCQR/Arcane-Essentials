package com.favouritedragon.arcaneessentials.common.spell.earth;

import com.favouritedragon.arcaneessentials.ArcaneEssentials;
import com.favouritedragon.arcaneessentials.common.entity.EntityMagicConstruct;
import com.favouritedragon.arcaneessentials.common.entity.EntityMagicSpawner;
import com.favouritedragon.arcaneessentials.common.entity.data.Behaviour;
import com.favouritedragon.arcaneessentials.common.entity.data.MagicConstructBehaviour;
import electroblob.wizardry.spell.Spell;
import electroblob.wizardry.util.AllyDesignationSystem;
import electroblob.wizardry.util.MagicDamage;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

import static com.favouritedragon.arcaneessentials.RegisterHandler.quake;

public class Quake extends Spell {

	public Quake() {
		super(ArcaneEssentials.MODID, "quake", EnumAction.BOW, false);
		addProperties(DAMAGE, DURATION, RANGE, BLAST_RADIUS);
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellModifiers modifiers) {
		return false;
	}

	public static class QuakeBehaviour extends MagicConstructBehaviour {

		@Override
		public Behaviour onUpdate(EntityMagicConstruct entity) {
			if (entity instanceof EntityMagicSpawner) {
				World world = entity.world;
				if (entity.ticksExisted % 3 == 0) {
					EntityFallingBlock block = new EntityFallingBlock(entity.world);
					block.setOrigin(entity.getPosition().add(0, 1, 0));
					block.setHurtEntities(true);
					world.spawnEntity(block);
				}
				List<Entity> nearby = world.getEntitiesWithinAABB(Entity.class, entity.getEntityBoundingBox().grow(quake.getProperty(BLAST_RADIUS).floatValue()));
				nearby.remove(entity.getCaster());
				if (!nearby.isEmpty()) {
					if (!world.isRemote) {
						for (Entity hit : nearby) {
							if (AllyDesignationSystem.isValidTarget(entity, hit)) {
								if (entity.canBeCollidedWith() && entity.canBePushed()) {
									hit.attackEntityFrom(MagicDamage.causeIndirectMagicDamage(entity, entity.getCaster()), 1);
									hit.addVelocity(entity.motionX / 2, entity.motionY / 2 + 0.15, entity.motionZ / 2);
								}
							}
						}
					}
				}
			}
			return this;
		}

		@Override
		public void fromBytes(PacketBuffer buf) {

		}

		@Override
		public void toBytes(PacketBuffer buf) {

		}

		@Override
		public void load(NBTTagCompound nbt) {

		}

		@Override
		public void save(NBTTagCompound nbt) {

		}
	}
}
