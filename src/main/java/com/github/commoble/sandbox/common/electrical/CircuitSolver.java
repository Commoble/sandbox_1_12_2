package com.github.commoble.sandbox.common.electrical;

import java.util.HashSet;

import com.github.commoble.sandbox.common.block.IElectricalBlock;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CircuitSolver
{
	public static boolean isCompleteCircuit(World world, BlockPos startPos)
	{
		// cycle detection in graphs:
		// for every visited node V, if there is an adjacent U such that U is already visited and U is not parent of V,
		// then there is a cycle in the graph
		// additionally, we are only interested if the starting node is part of a cycle
		// so we are only interested if this U is also the starting node
		
		// so check each node in a depth-first search, keeping track of
		//	-all previously visited nodes
		//	-the immediately-previously visited node
		//	-the first node to have been visited
		// check all adjacent nodes
		//	-if node is invalid (not electrical) ignore
		//	-if node has not been visited, visit it
		//	-otherwise, if previously-visited node is not immediately previously visited node AND is original node,
			//	then complete circuit has been found; otherwise ignore it
		HashSet<BlockPos> traversed = new HashSet<BlockPos>();
		traversed.add(startPos);
		
		return isCompleteCircuit(world, startPos, startPos, startPos, traversed);
	}
	
	private static boolean isCompleteCircuit(World world, BlockPos checkPos, BlockPos prevPos, BlockPos firstPos, HashSet<BlockPos> traversed)
	{
		for(EnumFacing face : EnumFacing.VALUES)
		{
			BlockPos nextCheck = checkPos.offset(face);
			if (world.getBlockState(nextCheck).getBlock() instanceof IElectricalBlock)
			{
				if (!traversed.contains(nextCheck))
				{
					traversed.add(nextCheck);
					return isCompleteCircuit(world, nextCheck, checkPos, firstPos, traversed);
				}
				else	// has been visited
				{
					if (!nextCheck.equals(prevPos) && nextCheck.equals(firstPos))
					{
						return true;
					}
				}
			}
			else	// not an electrical block
			{
				continue;
			}
		}
		
		return false;
	}
}
