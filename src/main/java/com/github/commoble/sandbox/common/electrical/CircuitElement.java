package com.github.commoble.sandbox.common.electrical;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A representation of a component in the circuit
 * indicating the component's position, as well as its adjacent
 * nodes and its electrical characteristics
 */
public class CircuitElement
{
	public BlockPos componentPos;
	public IBlockState componentState;
	public Node[] adjacentNodes;	// currently only two nodes are supported

	public CircuitElement(World world, BlockPos componentPos, Node nodeA, Node nodeB)
	{
		this.componentPos = componentPos;
		this.componentState = world.getBlockState(componentPos);
		this.adjacentNodes = new Node[] {nodeA, nodeB};
	}
	
	@Override
	public int hashCode()
	{
		return this.componentPos.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		return (o instanceof CircuitElement && ((CircuitElement)o).componentPos == this.componentPos);
	}
}
