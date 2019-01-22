package com.github.commoble.sandbox.common.tileentity;

import com.github.commoble.sandbox.common.block.IElectricalBlock;
import com.github.commoble.sandbox.common.electrical.ChargePacket;
import com.github.commoble.sandbox.common.electrical.CircuitSolver;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityBattery extends TileEntity implements ITickable
{
	public static final double NOMINAL_VOLTAGE = 12D;	// voltage output under ideal conditions
	public static final double MAX_CURRENT_DRAW = 30D;	// maximum current output in amps
	
	protected EnumFacing positiveSide;
	protected EnumFacing negativeSide;
	
	protected double real_voltage = NOMINAL_VOLTAGE;
	protected double real_current = 0D;

	public boolean circuit_update_check_pending = false;	// only set this on server
	

	@Override
	public void update()
	{
		if (this.circuit_update_check_pending)
		{
			System.out.println("Update pending");
			BlockPos selfPos = this.pos;
			BlockPos nextPos = selfPos.offset(this.positiveSide);
			BlockPos prevPos = selfPos.offset(this.negativeSide);
			IBlockState nextState = this.world.getBlockState(nextPos);
			Block nextBlock = nextState.getBlock();
			Block prevBlock = this.world.getBlockState(prevPos).getBlock();
			if (nextBlock instanceof IElectricalBlock && prevBlock instanceof IElectricalBlock)
			{
				// check if this is part of a complete circuit
				if (CircuitSolver.isCompleteCircuit(this.world, selfPos))
				{
					System.out.println("Complete circuit");
				}
				else
				{
					System.out.println("Not complete circuit");
				}
			}
			
			this.circuit_update_check_pending = false;
		}
	}
	
	public void setFacing(EnumFacing positiveSide)
	{
		this.positiveSide = positiveSide;
		this.negativeSide = positiveSide.getOpposite();
	}

	
}
