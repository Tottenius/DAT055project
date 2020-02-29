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
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canKill() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean intractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canWalkOn() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean hasDirections(Direction d) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setPrevPos() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void paintAsset(Graphics g, GamePanel gp) {
			// TODO Auto-generated method stub
			
		}
}
