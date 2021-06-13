package logistics.mate.dto;

public class DelayDto {

	
	private int Delay;
	private long milestoneId;
	public int getDelay() {
		return Delay;
	}
	public void setDelay(int delay) {
		Delay = delay;
	}
	public long getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	public DelayDto(int Delay, long milestoneId) {
		this.milestoneId = milestoneId;
		this.Delay = Delay;
	}
}
