package com.pRuneCraft;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Mouse;
import org.parabot.api.methods.Skill;
import org.parabot.api.script.Category;
import org.parabot.api.script.Script;
import org.parabot.api.script.ScriptDetails;
import org.parabot.api.script.Strategy;
import org.parabot.api.script.events.MessageEvent;
import org.parabot.api.util.Timer;
import org.parabot.api.wrappers.Item;
import org.parabot.core.bot.gui.BotGUI;
import org.parabot.core.bot.impl.listeners.MessageListener;
import org.parabot.core.bot.impl.listeners.Painter;

import com.pRuneCraft.strategies.Banking;
import com.pRuneCraft.strategies.Craft;
import com.pRuneCraft.strategies.LogIn;
import com.pRuneCraft.strategies.TeleportToAltar;
import com.pRuneCraft.strategies.TeleportToBank;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

@ScriptDetails(author = "pepsip77", category = Category.RUNECRAFTING, description = "SS runecrafter", name = "pRuneCraft", version = 0.1)
public class pRuneCraft extends Script implements Painter, MessageListener,
		MouseListener {

	private static ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	@Override
	public void onMessageReceived(MessageEvent message) {
		final String text = message.getMessage();

		if (text.contains("")) {

		}

	}

	@Override
	public void paint(Graphics g) {
		int Y = 200;
		int X = 15;
		int xpg = Skill.RUNECRAFTING.getExperience() - Data.startXp;
		int lvg = Skill.RUNECRAFTING.getLevel() - Data.startLv;
		g.drawString("pRuneCraft", X, Y += 15);
		g.drawString("Runtime: " + Data.time.toString(), X, Y += 15);
		g.drawString("Xp gain: " + xpg + " /h: " + Data.time.getPerHour(xpg),
				X, Y += 15);
		g.drawString("Level gain: " + lvg, X, Y += 15);
	}

	@Override
	protected boolean onExecute() {
		Data.startLv = Skill.RUNECRAFTING.getLevel();
		Data.startXp = Skill.RUNECRAFTING.getExperience();
		BotGUI.showLog();
		BotGUI.clearLog();
		strategies.add(new Banking());
		strategies.add(new Craft());
		strategies.add(new TeleportToAltar());
		strategies.add(new TeleportToBank());
		strategies.add(new LogIn());

		provide(strategies);
		Data.time = new Timer(0);

		return true;

	}

	@Override
	protected void onFinish() {
		strategies.remove(strategies);
		BotGUI.log("The script has been stopped.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = Mouse.getX();
		int y = Mouse.getY();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
