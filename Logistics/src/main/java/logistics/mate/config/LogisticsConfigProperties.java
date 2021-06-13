package logistics.mate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "Logistics")
public class LogisticsConfigProperties {
	public static class IncomeDrop{
		private int Under30m;
		private int Under60m;
		private int Under120m;
		private int Above120m;
		public int getUnder30m() {
			return Under30m;
		}
		public void setUnder30m(int under30m) {
			Under30m = under30m;
		}
		public int getUnder60m() {
			return Under60m;
		}
		public void setUnder60m(int under60m) {
			Under60m = under60m;
		}
		public int getUnder120m() {
			return Under120m;
		}
		public void setUnder120m(int under120m) {
			Under120m = under120m;
		}
		public int getAbove120m() {
			return Above120m;
		}
		public void setAbove120m(int above120m) {
			Above120m = above120m;
		}
	}
	private boolean Test;
	public boolean isTest() {
		return Test;
	}
	public void setTest(boolean test) {
		Test = test;
	}
	public IncomeDrop getIncomeDrop() {
		return incomeDrop;
	}
	public void setIncomeDrop(IncomeDrop incomeDrop) {
		this.incomeDrop = incomeDrop;
	}
	private IncomeDrop incomeDrop = new IncomeDrop();
}
