package com.pRuneCraft.strategies;

import org.parabot.api.methods.Bank;
import org.parabot.api.methods.Game;
import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Packets;
import org.parabot.api.methods.Players;
import org.parabot.api.script.Strategy;
import org.parabot.api.util.Time;
import org.parabot.api.wrappers.GameObject;

import com.pRuneCraft.Data;




public class Craft extends Strategy{

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return GameObjects.getNearest(Data.natAltar)!=null
				&&Inventory.getItem(Data.ess)!=null;
	}

	@Override
	public void run() {
		GameObject altar = GameObjects.getNearest(Data.natAltar);
		if ( altar != null) {
			Packets.sendAction(502, altar.getUID(), altar.getLocation().getX()
					- Game.getBaseX(),
					altar.getLocation().getY() - Game.getBaseY());
			Time.sleep(Data.sleep_time);

		sleep(2500, new Condition() {
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
