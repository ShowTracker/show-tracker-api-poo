package show.tracker.api.model.media;

public class TvShow extends Media {
	private String endYear;

	public TvShow(String endYear, int id, String year, String title, boolean isAdult) {
		super(id, year, title, isAdult);
		this.endYear = endYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

}
