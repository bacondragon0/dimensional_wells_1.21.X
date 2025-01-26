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

public class NetherWellBlock extends Block {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0, 0.0, 16.0, 8.0, 16.0);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

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
            // IRON TO GOLD (70%)
            if (itemEntity.getStack().getItem() == Items.IRON_INGOT) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.GOLD_INGOT.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.RAW_IRON) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.RAW_GOLD.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.IRON_NUGGET) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.GOLD_NUGGET.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.IRON_BLOCK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.GOLD_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // AMETHYST TO QUARTZ (100%)
            if (itemEntity.getStack().getItem() == Items.AMETHYST_SHARD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.QUARTZ.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // REDSTONE TO GLOWSTONE (90%)
            if (itemEntity.getStack().getItem() == Items.REDSTONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.GLOWSTONE_DUST.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // DIAMOND TO NETHERITE SCRAP (10%)
            if (itemEntity.getStack().getItem() == Items.DIAMOND) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.NETHERITE_SCRAP.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // OBSIDIAN TO CRYING OBSIDIAN (40%)
            if (itemEntity.getStack().getItem() == Items.OBSIDIAN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.4) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.CRYING_OBSIDIAN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // CRYING OBSIDIAN TO OBSIDIAN (100%)
            if (itemEntity.getStack().getItem() == Items.CRYING_OBSIDIAN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.OBSIDIAN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // BRICKS TO NETHER BRICKS (100%)
            if (itemEntity.getStack().getItem() == Items.BRICK) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.NETHER_BRICK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.BRICKS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.NETHER_BRICKS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // MUSHROOM TO FUNGUS (70%)
            if (itemEntity.getStack().getItem() == Items.RED_MUSHROOM) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.CRIMSON_FUNGUS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.BROWN_MUSHROOM) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.WARPED_FUNGUS.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // FUNGUS TO MUSHROOM (70%)
            if (itemEntity.getStack().getItem() == Items.CRIMSON_FUNGUS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.RED_MUSHROOM.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            if (itemEntity.getStack().getItem() == Items.WARPED_FUNGUS) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.7) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.BROWN_MUSHROOM.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // SAND INTO SOUL SAND (50%)
            if (itemEntity.getStack().getItem() == Items.SAND) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.SOUL_SAND.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // SLIMEBALL INTO MAGMA CREAM (50%)
            if (itemEntity.getStack().getItem() == Items.SLIME_BALL) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.MAGMA_CREAM.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // GRAVEL INTO MAGMA BLOCK (40%)
            if (itemEntity.getStack().getItem() == Items.GRAVEL) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.4) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.MAGMA_BLOCK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // TORCH INTO SOUL TORCH (90%)
            if (itemEntity.getStack().getItem() == Items.TORCH) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.SOUL_TORCH.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // LANTERN INTO SOUL LANTERN (90%)
            if (itemEntity.getStack().getItem() == Items.LANTERN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.SOUL_LANTERN.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // CAMPFIRE INTO SOUL CAMPFIRE (90%)
            if (itemEntity.getStack().getItem() == Items.CAMPFIRE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.9) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.SOUL_CAMPFIRE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // STONE INTO BLACKSTONE (50%)
            if (itemEntity.getStack().getItem() == Items.STONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 0.5) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.BLACKSTONE.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // COBBLESTONE INTO NETHERRACK (100%)
            if (itemEntity.getStack().getItem() == Items.COBBLESTONE) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() <= 1.0) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.NETHERRACK.getDefaultStack()));
                    } else {
                        AnnihilationParticles(world,pos);
                    }
                }
            }
            // SEA LANTERN INTO FROGLIGHT (33% Each)
            if (itemEntity.getStack().getItem() == Items.SEA_LANTERN) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));
                for (int i = 0; i < itemAmount; i++) {
                    double temp = Math.random();
                    if (temp < 0.33) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.PEARLESCENT_FROGLIGHT.getDefaultStack())); }
                    if (temp >= 0.33 && temp <= 0.66) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.VERDANT_FROGLIGHT.getDefaultStack())); }
                    if (temp > 0.66) {
                        TransformationParticles(world,pos);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, Items.OCHRE_FROGLIGHT.getDefaultStack())); }
                }
            }

            // RETURN INVALID ITEM
            else {
                int itemAmount = itemEntity.getStack().getCount();
                ItemStack item = itemEntity.getStack();
                itemEntity.setStack(new ItemStack(Items.AIR));

                for (int i = 0; i < itemAmount; i++) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                            5, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                            5, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.7f, pos.getY() + 0.7f, pos.getZ() + 0.7f, item));
                }

            }
            world.playSoundFromEntity(null, entity, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 1.0f, 0.6f);
        }
    }

    public NetherWellBlock(Settings settings) {
        super(settings);
    }
}
