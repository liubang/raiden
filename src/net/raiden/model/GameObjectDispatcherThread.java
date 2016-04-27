package net.raiden.model;

import java.io.IOException;
import java.util.Properties;

public class GameObjectDispatcherThread extends Thread
{
	private int paintCount;
	Properties props = new Properties();
	private boolean flag;

	public GameObjectDispatcherThread() {
		try {
			props.load(GameObjectDispatcherThread.class.getClassLoader()
					.getResourceAsStream("config/stage_" + Game.getInstance().getStep() + ".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run()
	{
		// 启动游戏后才进行游戏物体派发
		while (!flag) {
			paintCount++;
			this.createGameObject();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void createGameObject()
	{
		String desc = props.getProperty("s" + paintCount);
		if (desc != null) {
			try {
				if (desc.indexOf("_") > 0) {
					int count = Integer.parseInt(desc.split("_")[1]);
					for (int i = 0; i < count; i++) {
						Class.forName(desc.split("_")[0]).newInstance();
					}
				} else {
					Class.forName(desc).newInstance();
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void interrupt()
	{
		flag = true;
	}

}
