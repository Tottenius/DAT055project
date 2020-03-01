package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;

public class Empty extends AbstractAsset{
		public Empty(int position) {
			super(position);
	}

		@Override
		public boolean killable() {
			return false;
		}

		@Override
		public boolean canKill() {
			return false;
		}

		@Override
		public boolean intractable() {
			return false;
		}

		@Override
		public boolean canWalkOn() {
			return true;
		}

		@Override
		public boolean hasDirections(Direction d) {
			return false;
		}

		@Override
		public void paintAsset(Graphics g, GamePanel gp) {

		}
}
