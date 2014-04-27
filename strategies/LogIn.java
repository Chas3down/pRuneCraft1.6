package com.pRuneCraft.strategies;

import org.parabot.api.methods.Game;
import org.parabot.api.methods.Mouse;
import org.parabot.api.methods.Players;
import org.parabot.api.script.Strategy;
import org.parabot.api.util.Time;

import com.pRuneCraft.Data;

public class LogIn extends Strategy {

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return !Game.isLoggedIn();
	}

	@Override
	public void run() {
		int a = 0;
		boolean bbreak = false;

		Mouse.hop(365, 345);
		Mouse.click(true);
		Time.sleep(2000);
		Data.lastAnim = System.currentTimeMillis();
		while ((System.currentTimeMillis() - Data.lastAnim) > 2000 && !bbreak) {
			if (!Game.isLoggedIn()) {
				Data.lastAnim = System.currentTimeMillis();
			}
			a++;
			if (a > 400) {
				bbreak = true;
			}
			Time.sleep(50);
		}

	}

}
