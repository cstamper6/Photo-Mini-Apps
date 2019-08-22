package miniapps;

public interface ROIObserver {

	void notify(ObservablePicture picture, Region changed_region);
}
