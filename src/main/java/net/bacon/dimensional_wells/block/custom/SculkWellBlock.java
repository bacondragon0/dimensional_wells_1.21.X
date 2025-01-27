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

    float v = 0.7f;

    boolean trigger = false;

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
            // AMETHYST SHARD TO ECHO SHARD (70%)
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
                trigger = true;
            }
            // QUARTZ TO AMETHYST (70%)
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
                trigger = true;
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
                trigger = true;
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
                trigger = true;
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
                trigger = true;
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
                trigger = true;
            }
            // COBBLESTONE TO COBBLED DEEPSLATE (100%)
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COBBLED_DEEPSLATE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE_STAIRS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COBBLED_DEEPSLATE_STAIRS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE_SLAB) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COBBLED_DEEPSLATE_SLAB.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE_WALL) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.COBBLED_DEEPSLATE_WALL.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // STONE TO POLISHED DEEPSLATE (100%)
            if (itemEntity.getStack().getItem() == Items.STONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_DEEPSLATE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_STAIRS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_DEEPSLATE_STAIRS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_SLAB) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_DEEPSLATE_SLAB.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // STONE BRICKS TO DEEPSLATE BRICKS (100%)
            if (itemEntity.getStack().getItem() == Items.STONE_BRICKS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DEEPSLATE_BRICKS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_BRICK_STAIRS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DEEPSLATE_BRICK_STAIRS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_BRICK_SLAB) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DEEPSLATE_BRICK_SLAB.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.CRACKED_STONE_BRICKS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CRACKED_DEEPSLATE_BRICKS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_BRICK_WALL) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DEEPSLATE_BRICK_WALL.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.CHISELED_STONE_BRICKS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CHISELED_DEEPSLATE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // VINES TO GLOW LICHEN (60%)
            if (itemEntity.getStack().getItem() == Items.VINE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.60) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.GLOW_LICHEN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
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
                trigger = true;
            }
            // RETURN INVALID ITEM
            else if (!trigger) {
                itemEntity.addVelocity(ThreadLocalRandom.current().nextDouble(-0.3, 0.3),0.4, ThreadLocalRandom.current().nextDouble(-0.3, 0.3));
                for (int i = 0; i < 1; i++) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                            3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                            3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
                }
            }
        }
        trigger = false;
    }

    public SculkWellBlock(Settings settings) {
        super(settings);
    }
}
