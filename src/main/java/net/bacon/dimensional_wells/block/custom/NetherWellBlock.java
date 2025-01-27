package net.bacon.dimensional_wells.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;

import static net.bacon.dimensional_wells.config.ModConfigs.BUILDING_BLOCK_CONVERSION_CHANCE;
import static net.bacon.dimensional_wells.config.ModConfigs.ITEM_DISINTEGRATION;

public class NetherWellBlock extends Block {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0, 0.0, 16.0, 8.0, 16.0);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    float v = 0.7f;

    boolean trigger = false;

    private void TransformationParticles(World world, BlockPos pos) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
        ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
    }

    private void AnnihilationParticles(World world, BlockPos pos) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
        ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity && world instanceof ServerWorld) {
            world.playSoundFromEntity(null, entity, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 1.0f, 0.6f);
            // AMETHYST TO QUARTZ (100%)
            if (itemEntity.getStack().getItem() == Items.AMETHYST_SHARD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.QUARTZ.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // REDSTONE TO GLOWSTONE (90%)
            if (itemEntity.getStack().getItem() == Items.REDSTONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.GLOWSTONE_DUST.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // OBSIDIAN TO CRYING OBSIDIAN (40%)
            if (itemEntity.getStack().getItem() == Items.OBSIDIAN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.4 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CRYING_OBSIDIAN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // CRYING OBSIDIAN TO OBSIDIAN (100%)
            if (itemEntity.getStack().getItem() == Items.CRYING_OBSIDIAN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.OBSIDIAN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // BRICKS TO NETHER BRICKS (100%)
            if (itemEntity.getStack().getItem() == Items.BRICK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.NETHER_BRICK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.BRICKS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.NETHER_BRICKS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // MUSHROOM TO FUNGUS (70%)
            if (itemEntity.getStack().getItem() == Items.RED_MUSHROOM) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CRIMSON_FUNGUS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.BROWN_MUSHROOM) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.WARPED_FUNGUS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // CRIMSON FUNGUS TO MUSHROOM (70%)
            if (itemEntity.getStack().getItem() == Items.CRIMSON_FUNGUS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.RED_MUSHROOM.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // MYCELIUM TO NYLIUM (100%)
            if (itemEntity.getStack().getItem() == Items.MYCELIUM) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CRIMSON_NYLIUM.getDefaultStack()));
                    } else {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.WARPED_NYLIUM.getDefaultStack()));
                    }
                }
                trigger = true;
            }
            // VINE TO NETHER VINES
            if (itemEntity.getStack().getItem() == Items.VINE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.WEEPING_VINES.getDefaultStack()));
                    } else {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.TWISTING_VINES.getDefaultStack()));
                    }
                }
                trigger = true;
            }
            // WARPED FUNGUS TO BROWN MUSHROOM (70%)
            if (itemEntity.getStack().getItem() == Items.WARPED_FUNGUS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.BROWN_MUSHROOM.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // SAND INTO SOUL SAND (50%)
            if (itemEntity.getStack().getItem() == Items.SAND) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.SOUL_SAND.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // SLIMEBALL INTO MAGMA CREAM (50%)
            if (itemEntity.getStack().getItem() == Items.SLIME_BALL) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.MAGMA_CREAM.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // GRAVEL INTO MAGMA BLOCK (40%)
            if (itemEntity.getStack().getItem() == Items.GRAVEL) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.4 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.MAGMA_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // TORCH INTO SOUL TORCH (90%)
            if (itemEntity.getStack().getItem() == Items.TORCH) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.SOUL_TORCH.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // LANTERN INTO SOUL LANTERN (90%)
            if (itemEntity.getStack().getItem() == Items.LANTERN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.SOUL_LANTERN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // CAMPFIRE INTO SOUL CAMPFIRE (90%)
            if (itemEntity.getStack().getItem() == Items.CAMPFIRE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.SOUL_CAMPFIRE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // COBBLESTONE INTO BLACKSTONE (100%)
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.BLACKSTONE.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.BLACKSTONE_STAIRS.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.BLACKSTONE_SLAB.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.BLACKSTONE_WALL.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // STONE INTO POLISHED BLACKSTONE (100%)
            if (itemEntity.getStack().getItem() == Items.STONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE_STAIRS.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE_SLAB.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // STONE BRICKS TO BLACKSTONE BRICKS (100%)
            if (itemEntity.getStack().getItem() == Items.STONE_BRICKS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE_BRICKS.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE_BRICK_SLAB.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CHISELED_POLISHED_BLACKSTONE.getDefaultStack()));
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
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }

            // DIRT INTO NETHERRACK (100%)
            if (itemEntity.getStack().getItem() == Items.DIRT) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.NETHERRACK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // STEM INTO NETHER STEM (100%)
            if (itemEntity.getStack().getItem() == Items.MUSHROOM_STEM) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5f) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CRIMSON_STEM.getDefaultStack()));
                    } else {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.WARPED_STEM.getDefaultStack()));
                    }
                }
                trigger = true;
            }
            // ROSE BUSH TO WITHER ROSE (10%)
            if (itemEntity.getStack().getItem() == Items.ROSE_BUSH) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.1 || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.WITHER_ROSE.getDefaultStack()));
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

    public NetherWellBlock(Settings settings) {
        super(settings);
    }
}
