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

import static net.bacon.dimensional_wells.config.ModConfigs.*;

public class EndWellBlock extends Block {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0, 0.0, 16.0, 8.0, 16.0);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private void TransformationParticles(World world, BlockPos pos) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.REVERSE_PORTAL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                6, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
        ((ServerWorld) world).spawnParticles(ParticleTypes.REVERSE_PORTAL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                6, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
    }

    private void AnnihilationParticles(World world, BlockPos pos) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                4, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                4, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
    }

    float v = 0.7f;

    boolean trigger = false;

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity && world instanceof ServerWorld) {
            world.playSoundFromEntity(null, entity, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 1.0f, 0.6f);
            // STONE TO END STONE (100%)
            if (itemEntity.getStack().getItem() == Items.STONE && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_STONE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // COBBLESTONE TO END STONE (100%)
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_STONE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // NAUTILUS SHELL TO SHULKER SHELL (80%)
            if (itemEntity.getStack().getItem() == Items.NAUTILUS_SHELL && NAUTILUS_SHELL_TO_SHULKER_SHELL_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= NAUTILUS_SHELL_TO_SHULKER_SHELL_CHANCE || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world, pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.SHULKER_SHELL.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world, pos);
                    }
                }
                trigger = true;
            }
            // WATER BOTTLE TO DRAGON BREATH (100%)
            if (itemEntity.getStack().getItem() == Items.POTION && WATER_BOTTLE_TO_DRAGON_BREATH_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= WATER_BOTTLE_TO_DRAGON_BREATH_CHANCE || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world, pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.DRAGON_BREATH.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world, pos);
                    }
                }
                trigger = true;
            }
            // APPLE TO CHORUS FRUIT (70%)
            if (itemEntity.getStack().getItem() == Items.APPLE && APPLE_TO_CHORUS_FRUIT_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= APPLE_TO_CHORUS_FRUIT_CHANCE || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world, pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.CHORUS_FRUIT.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world, pos);
                    }
                }
                trigger = true;
            }
            // TORCH TO END ROD (30%)
            if (itemEntity.getStack().getItem() == Items.TORCH && TORCH_TO_END_ROD_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= TORCH_TO_END_ROD_CHANCE || !ITEM_DISINTEGRATION) {
                        TransformationParticles(world, pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_ROD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world, pos);
                    }
                }
                trigger = true;
            }
            // STONE BRICKS TO ENDSTONE BRICKS
            if (itemEntity.getStack().getItem() == Items.STONE_BRICKS && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_STONE_BRICKS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_BRICK_STAIRS && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_STONE_BRICK_STAIRS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_BRICK_SLAB && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_STONE_BRICK_SLAB.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.STONE_BRICK_WALL && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.END_STONE_BRICK_WALL.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // QUARTZ BLOCKS TO PURPUR BLOCKS
            if (itemEntity.getStack().getItem() == Items.QUARTZ_BLOCK && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.PURPUR_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.QUARTZ_STAIRS && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.PURPUR_STAIRS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.QUARTZ_SLAB && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.PURPUR_SLAB.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            if (itemEntity.getStack().getItem() == Items.QUARTZ_PILLAR && BUILDING_BLOCK_CONVERSION_CHANCE > 0) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= BUILDING_BLOCK_CONVERSION_CHANCE) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + v, pos.getY() + v, pos.getZ() + v, Items.PURPUR_PILLAR.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
                trigger = true;
            }
            // RETURN INVALID ITEM
            else if (!trigger) {
                itemEntity.addVelocity(ThreadLocalRandom.current().nextDouble(-0.3, 0.3),0.4, ThreadLocalRandom.current().nextDouble(-0.4, 0.4));
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

    public EndWellBlock(Settings settings) {
        super(settings);
    }
}
