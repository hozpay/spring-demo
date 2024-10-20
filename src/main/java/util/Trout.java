package util;

public final class Trout extends FishSealed {
    // final keyword in this class , presence of final keyword restrict it from being extended further.
    // Now Trout class is not extendable
    public String getTroutName() {
        return "My name is Trout Fish";
    }
}
