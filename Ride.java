public class Ride {
    private String name;
    private int minAge;
    private float minWeight;
    private float minHeight;
    private float cost;
// isOpen?

    public Ride(String name, int minAge, float minWeight, float minHeight, float cost) {
        this.name = name;
        this.minAge = minAge;
        this.minWeight = minWeight;
        this.minHeight = minHeight;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(float minWeight) {
        this.minWeight = minWeight;
    }

    public float getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(float minHeight) {
        this.minHeight = minHeight;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean GivePermission(){ return true; }
}

