package net.bacon.dimensional_wells.block.custom;

import net.bacon.dimensional_wells.DimensionalWells;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SculkWellBlock extends Block {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0, 0.0, 16.0, 8.0, 16.0);

    float v = 0.8f;

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private void TransformationParticles(World world, BlockPos pos) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
        ((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
    }

    private void AnnihilationParticles(World world, BlockPos pos) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
        ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity && world instanceof ServerWorld) {
            world.playSoundFromEntity(null, entity, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 1.0f, 0.6f);
            // AMETHYST SHARD TO ECHO SHARD (70% Both ways)
            if (itemEntity.getStack().getItem() == Items.AMETHYST_SHARD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.ECHO_SHARD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.ECHO_SHARD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.AMETHYST_SHARD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // QUART TO AMETHYST (70%)
            if (itemEntity.getStack().getItem() == Items.QUARTZ) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.AMETHYST_SHARD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // MOSS TO SCULK (80%)
            if (itemEntity.getStack().getItem() == Items.MOSS_BLOCK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.8) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.SCULK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // SCULK TO MUD (80%)
            if (itemEntity.getStack().getItem() == Items.SCULK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.8) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.MUD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // MUD TO DIRT (100%)
            if (itemEntity.getStack().getItem() == Items.MUD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DIRT.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // DIRT TO MOSS (30%)
            if (itemEntity.getStack().getItem() == Items.DIRT) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.3f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.MOSS_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // IRON TO COPPER (95% Ingot, 80% Raw)
            if (itemEntity.getStack().getItem() == Items.IRON_INGOT) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.95f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COPPER_INGOT.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.IRON_BLOCK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.95f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COPPER_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.RAW_IRON) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.8f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.RAW_COPPER.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.RAW_IRON_BLOCK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.8f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.RAW_COPPER_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // COPPER TO IRON (50% Ingot, 35% Raw)
            if (itemEntity.getStack().getItem() == Items.COPPER_INGOT) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.50f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.IRON_INGOT.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.COPPER_BLOCK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.50f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.IRON_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.RAW_COPPER) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.35f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.RAW_IRON.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.RAW_COPPER_BLOCK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.35f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.RAW_IRON_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // SWEET BERRY TO GLOW BERRY (75%)
            if (itemEntity.getStack().getItem() == Items.SWEET_BERRIES) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.75f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.GLOW_BERRIES.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // INK SAC TO GLOW INK SAC (80%)
            if (itemEntity.getStack().getItem() == Items.INK_SAC) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.75f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.GLOW_INK_SAC.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // GLOW INK SAC TO INK SAC (90%)
            if (itemEntity.getStack().getItem() == Items.GLOW_INK_SAC) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.75f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.INK_SAC.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // BLAZE ROD TO BREEZE ROD (50%)
            if (itemEntity.getStack().getItem() == Items.BLAZE_ROD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.50f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.BREEZE_ROD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // STONE TO DEEPSLATE (100%)
            if (itemEntity.getStack().getItem() == Items.STONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.50f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DEEPSLATE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // COBBLESTONE TO COBBLED DEEPSLATE (100%)
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.50f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COBBLED_DEEPSLATE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // GLASS TO TINTED_GLASS (25%)
            if (itemEntity.getStack().getItem() == Items.GLASS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.25f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.TINTED_GLASS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // RETURN INVALID ITEM
            else {
                itemEntity.addVelocity(ThreadLocalRandom.current().nextDouble(-0.3, 0.3),0.1f, ThreadLocalRandom.current().nextDouble(-0.3, 0.3));
                for (int i = 0; i < 1; i++) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                            3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                            3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
                }
            }
        }
    }

    public SculkWellBlock(Settings settings) {
        super(settings);
    }
}
