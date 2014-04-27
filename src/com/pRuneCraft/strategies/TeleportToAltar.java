package com.pRuneCraft.strategies;

import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Packets;
import org.parabot.api.methods.Players;
import org.parabot.api.script.Strategy;
import org.parabot.api.util.Time;
import com.pRuneCraft.Data;

public class TeleportToAltar extends Strategy {

	@Override
	public boolean isValid() {

		return GameObjects.getNearest(Data.natAltar) == null
				&& Inventory.getItem(Data.ess) != null
				&& Players.getMyPlayer().getAnimation() == -1;
	}

	@Override
	public void run() {
		// 867 menuAction1 : 18341 menuAction2: 3 menuAction3: 1688 staff
		// operation
		Packets.sendAction(867, Data.natStaff, 3, 1688);
		Time.sleep(Data.sleep_time);
		Packets.sendAction(-1, -1, -1, -1);
		Time.sleep(Data.sleep_time * 10);
		sleep(4500, new Condition() {
			@Override
			public boolean validate() {
				return Players.getMyPlayer().getAnimation() != -1;
			}

		});
	}

	public void sleep(int time, Condition c) {
		for (int i = 0; i < time / 50 && !c.validate(); i++) {
			Time.sleep(time / 50);
		}
	}

	public interface Condition {
		public abstract boolean validate();
	}

}
