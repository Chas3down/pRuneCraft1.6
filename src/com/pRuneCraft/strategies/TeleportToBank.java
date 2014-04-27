package com.pRuneCraft.strategies;

import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Interfaces;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Packets;
import org.parabot.api.methods.Players;
import org.parabot.api.script.Strategy;
import org.parabot.api.util.Time;

import com.pRuneCraft.Data;
import com.pRuneCraft.strategies.Craft.Condition;

public class TeleportToBank extends Strategy {

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return GameObjects.getNearest(Data.bank) == null
				&& Inventory.getItem(Data.ess) == null
				&&Players.getMyPlayer().getAnimation()==-1;
	}

	@Override
	public void run() {
		// menuActionId: 315 menuAction1 : -1 menuAction2: -1 menuAction3: 1164
		// //spell cast

		// menuActionId: 315 menuAction1 : -1 menuAction2: -1 menuAction3:
		// 2496//draynor
		Packets.sendAction(315, -1, -1, 1164);
		Time.sleep(Data.sleep_time);
		Packets.sendAction(-1, -1, -1, -1);
		Time.sleep(Data.sleep_time * 10);

		if (Interfaces.getInterface(Data.interfaceId) != null) {
			Packets.sendAction(315, -1, -1, 2496);
			Time.sleep(Data.sleep_time);
			Packets.sendAction(-1, -1, -1, -1);
			Time.sleep(Data.sleep_time * 5);
			sleep(4500, new Condition() {
				@Override
				public boolean validate() {
					return Players.getMyPlayer().getAnimation() != -1;
				}

			});
		}

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
