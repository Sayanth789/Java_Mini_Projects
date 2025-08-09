public class InstrumentSpec {
    private String builder;
    private String model;
    private String type;
    private String backWood;
    private String topWood;

    public InstrumentSpec(String builder, String model, String type, String backWood, String topWood) {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public String getBuilder() { return builder; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public String getBackWood() { return backWood; }
    public String getTopWood() { return topWood; }
}
