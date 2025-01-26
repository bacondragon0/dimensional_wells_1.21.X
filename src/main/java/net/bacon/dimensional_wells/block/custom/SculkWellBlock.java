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
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import java.util.Random;

public class SculkWellBlock extends Block {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0, 0.0, 16.0, 8.0, 16.0);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity && world instanceof ServerWorld) {
            if (itemEntity.getStack().getItem() == Items.AMETHYST_SHARD) {
                int itemAmount = itemEntity.getStack().getCount();
                itemEntity.setStack(new ItemStack(Items.AIR));

                for (int i = 0; i < itemAmount; i++) {
                    if (Math.random() > 0.5) {
                        ((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                                3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
                        ((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                                3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 0.7f, pos.getZ() + 0.5f, Items.ECHO_SHARD.getDefaultStack()));
                        DimensionalWells.LOGGER.info("Generating sculk");
                    } else {
                        ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                                3, Math.random() / 5, (Math.random() / 10) * -1,Math.random() / 5,0.1f);
                        ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5f,
                                3, Math.random() / 5, (Math.random() / 10) * 1,Math.random() / 5,0.1f);
                        DimensionalWells.LOGGER.info("Generating air");
                    }
                    DimensionalWells.LOGGER.info("Iterating");
                }
            }
        }
    }

    public SculkWellBlock(Settings settings) {
        super(settings);
    }
}
