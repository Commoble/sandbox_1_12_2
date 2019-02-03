package com.github.commoble.sandbox.common.electrical;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.github.commoble.sandbox.common.block.CategoriesOfBlocks;
import com.github.commoble.sandbox.common.block.IElectricalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Circuit
{
	public HashSet<Node> nodes = new HashSet<Node>();
	
	public boolean isPositionInAnyKnownNode(BlockPos pos)
	{
		for (Node node : this.nodes)
		{
			if (node.contains(pos))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Given a starting ground node, build the rest of the circuit and return that circuit
	 */
	public static Circuit buildCircuitFromGround(World world, Node groundNode)
	{
		Circuit circuit = new Circuit();
		
		circuit = expandCircuitFromNode(world, circuit, groundNode);		
		
		return circuit;
	}
	
	private static Circuit expandCircuitFromNode(World world, Circuit circuit, Node baseNode)
	{
		// for each component the node touches,
		// ASSUMPTION: "components" only have two connecting sides
		if (circuit.nodes.contains(baseNode))
		{
			return circuit;
		}
		System.out.println("Adding node to circuit");
		circuit.nodes.add(baseNode);
		
		// the node builder won't add a component if there's no nodes attached to at least two sides of the component
		
		for (BlockPos componentPos : baseNode.connectedComponents)
		{
			Set<EnumFacing> checkFaces;
			IBlockState componentState = world.getBlockState(componentPos);
			Block componentBlock = componentState.getBlock();
			if (componentBlock instanceof IElectricalBlock)
			{
				checkFaces = ((IElectricalBlock)componentBlock).getConnectingFaces(world, componentState, componentPos);
			}
			else
			{	
				System.out.println("Electrical component at " + componentPos.toString() + " not marked as electrical block! This is an error");
				return null;
			}
			
			BlockPos prevPos = null;	// wire block on old node
			BlockPos nextPos = null;	// wire block on new node

			// components should have exactly 2 connections
			// less than 2 -- remove it from node
			// more than 2 -- invalid component
			
			// HACK until lightbulb connections are done correctly
			for (EnumFacing face : checkFaces)
			{
				BlockPos checkPos = componentPos.offset(face);
				IBlockState checkState = world.getBlockState(checkPos);
				Block checkBlock = checkState.getBlock();
				if ((world.getBlockState(checkPos).getBlock() instanceof IElectricalBlock))
				{
					if (prevPos == null && baseNode.contains(checkPos))
					{
						prevPos = checkPos;
					}
					else if (nextPos == null)
					{
						nextPos = checkPos;
					}
					else
					{
						System.out.println("Component found at " + componentPos.toString() + " has more than two connections! This is not supported yet");
						return null;
					}
				}
			}
			
			if (prevPos == null || nextPos == null)
			{	// not enough connections
				if (prevPos != null)
				{
					System.out.println("Adding dead node");
					Node node = Node.createDeadNode(prevPos);
					circuit.nodes.add(node);
					continue;
				}
				else if (nextPos != null)
				{
					System.out.println("Adding dead node");
					Node node = Node.createDeadNode(nextPos);
					circuit.nodes.add(node);
					continue;
				}
				else
				{
					System.out.println("Component at position " + componentPos.toString() + " has no nodes attached! How did this even happen");
				}
			}
			// end HACK
			
			Block prevBlock = world.getBlockState(prevPos).getBlock();
			Block nextBlock = world.getBlockState(nextPos).getBlock();
			
			// if next node is a virtual node
			if (CategoriesOfBlocks.isAnyComponentBlock(nextBlock))
			{
				Node node = Node.createVirtualNode(componentPos, nextPos);
				circuit = circuit.expandCircuitFromNode(world, circuit, node);
				continue;
			}
			else	// next node starts with a wire block
			{
				if (circuit.isPositionInAnyKnownNode(nextPos)) // if position is already in circuit, ignore
				{
					continue;
				}
				else // new node
				{
					Node node = Node.buildNodeFrom(world, componentPos, nextPos);
					circuit = circuit.expandCircuitFromNode(world, circuit, node);
				}
			}
		}
		
		return circuit;
	}
	
	public void printToConsole(World world)
	{
		HashMap<BlockPos, String> vComponents = new HashMap<BlockPos, String>();
		HashMap<BlockPos, String> rComponents = new HashMap<BlockPos, String>();
		
		int nodeID = 0;
		int vID = 1;
		int rID = 1;
		for (Node node : this.nodes)
		{
			String nodeLine = "Node " + nodeID + ":";
			LinkedList<String> vList = new LinkedList<String>();
			LinkedList<String> rList = new LinkedList<String>();
			
			for (BlockPos pos : node.connectedComponents)
			{
				Block componentBlock = world.getBlockState(pos).getBlock();
				if (CategoriesOfBlocks.activeComponentBlocks.contains(componentBlock))
				{
					if (vComponents.containsKey(pos))
					{
						vList.add(vComponents.get(pos));
					}
					else
					{
						String vName = "V" + vID;
						vID++;
						vComponents.put(pos, vName);
						vList.add(vName);
					}
				}
				if (CategoriesOfBlocks.passiveComponentBlocks.contains(componentBlock))
				{
					if (rComponents.containsKey(pos))
					{
						rList.add(rComponents.get(pos));
					}
					else
					{
						String rName = "R" + rID;
						rID++;
						rComponents.put(pos, rName);
						rList.add(rName);
					}
				}
			}

			vList.sort(null);
			rList.sort(null);
			
			for (String s : vList)
			{
				nodeLine = nodeLine + " " + s;
			}
			for (String s : rList)
			{
				nodeLine = nodeLine + " " + s;
			}
			
			System.out.println(nodeLine);
			
			nodeID++;
		}
	}
}
