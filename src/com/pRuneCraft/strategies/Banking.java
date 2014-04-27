package com.pRuneCraft.strategies;

import org.parabot.api.methods.Bank;
import org.parabot.api.methods.Game;
import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Packets;
import org.parabot.api.script.Strategy;
import org.parabot.api.util.Time;
import org.parabot.api.wrappers.BankItem;
import org.parabot.api.wrappers.GameObject;
import org.parabot.api.wrappers.Item;

import com.pRuneCraft.Data;

public class Banking extends Strategy {

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return Inventory.getItem(Data.ess) == null
				&& GameObjects.getNearest(Data.bank) != null;
	}

	@Override
	public void run() {
		GameObject booth = GameObjects.getNearest(Data.bank);
		if (!Bank.isOpen() && booth != null) {
			Packets.sendAction(502, booth.getUID(), booth.getLocation().getX()
					- Game.getBaseX(),
					booth.getLocation().getY() - Game.getBaseY());
			Time.sleep(Data.sleep_time);
			Data.tradeTime = System.currentTimeMillis();
			while (System.currentTimeMillis() - Data.tradeTime < 2500
					&& !Bank.isOpen()) {
				Time.sleep(Data.sleep_time);
			}
		}

		if (Bank.isOpen()) {
			Item runes = Inventory.getItem(Data.natRune);
			if (runes != null) {
				Packets.sendAction(646, -1, -1, 19000);
				Time.sleep(Data.sleep_time);
				Packets.sendAction(-1, -1, -1, -1);
				Time.sleep(Data.sleep_time * 5);
			} else {
				BankItem items[] = Bank.getBankItems();
				if (items != null && items.length > 0) {
					for (int i = 0; i < items.length; i++) {
						if (items[i].getId() == Data.ess) {
							Packets.sendAction(431, Data.ess, i, 5382);
							Time.sleep(Data.sleep_time * 5);
							Packets.sendAction(-1, -1, -1, -1);
							Time.sleep(Data.sleep_time * 5);
							Packets.sendAction(200, -1, -1, 5384);
							Time.sleep(Data.sleep_time * 5);
							Packets.sendAction(-1, -1, -1, -1);
							Time.sleep(Data.sleep_time * 5);
							break;
						}
					}
				}
			}

		}

	}

}
