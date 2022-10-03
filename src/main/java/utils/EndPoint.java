package utils;

public enum EndPoint {
    CreateBookingAPI("/booking"),
    CreateAuth("/auth");
    private String resource;

    EndPoint(String resource) {
        this.resource=resource;
    }

    public String getResource() {
        return resource;
    }
}
