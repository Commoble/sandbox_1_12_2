package com.github.commoble.sandbox.common.electrical;

import java.util.HashSet;

import com.github.commoble.sandbox.common.block.IElectricalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
		// also the first block must be electrical

		HashSet<BlockPos> traversed = new HashSet<BlockPos>();
		
		return isCompleteCircuit(world, startPos, startPos, startPos, traversed);
	}
	
	private static boolean isCompleteCircuit(World world, BlockPos checkPos, BlockPos prevPos, BlockPos firstPos, HashSet<BlockPos> traversed)
	{
		// add to traversed even if not electrical so it still won't be checked twice
		// doing it this way, however, means that firstpos must be electrical for it the implementation to work
		traversed.add(checkPos);
		IBlockState checkState = world.getBlockState(checkPos);
		Block checkBlock = checkState.getBlock();
		if (checkBlock instanceof IElectricalBlock)
		{
			for(EnumFacing face : ((IElectricalBlock)checkBlock).getConnectingFaces(world, checkState, checkPos))
			{
				BlockPos nextCheck = checkPos.offset(face);

				if (!traversed.contains(nextCheck))
				{
					boolean found = isCompleteCircuit(world, nextCheck, checkPos, firstPos, traversed);
					if (found) return true; 
				}
				else	// has been visited
				{
					if (!nextCheck.equals(prevPos) && nextCheck.equals(firstPos))
					{
						return true;
					}
				}
			}
			
			return false;
		}
		else // not electrical block
		{
			return false;
		}
	}
}
