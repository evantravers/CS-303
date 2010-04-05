package cs303;

public class Cities {
    private final String[] names = {
        "Atlanta,GA",
        "Augusta,GA",
        "Biloxi,MS",
        "Birmingham,AL",
        "Chattanooga,TN",
        "Columbus,GA",
        "Gadsden,AL",
        "Gulfport,MS",
        "Huntsville,AL",
        "Jackson,MS",
        "Knoxville,TN",
        "Macon,GA",
        "Memphis,TN",
        "Mobile,AL",
        "Montgomery,AL",
        "Nashville,TN",
        "Natchez,MS",
        "Savannah,GA",
    };
    private final double[][] distances = {
        {0, 140, 350, 139, 103, 95, 94, 361, 142, 350, 155, 76, 336, 300, 146, 215, 435, 222},
        {140, 0, 457, 278, 219, 188, 234, 469, 277, 483, 204, 105, 475, 403, 262, 330, 564, 108},
        {350, 457, 0, 248, 382, 270, 301, 12, 328, 152, 479, 352, 335, 54, 204, 416, 169, 474},
        {139, 278, 248, 0, 135, 128, 57, 255, 84, 213, 234, 189, 216, 208, 84, 182, 300, 345},
        {103, 219, 382, 135, 0, 179, 81, 390, 75, 338, 100, 180, 267, 339, 193, 113, 426, 317},
        {95, 188, 270, 128, 179, 0, 122, 281, 181, 303, 249, 83, 344, 217, 76, 275, 380, 228},
        {94, 234, 301, 57, 81, 122, 0, 309, 59, 269, 178, 159, 242, 258, 115, 155, 356, 313},
        {361, 469, 12, 255, 390, 281, 309, 0, 334, 148, 488, 363, 335, 66, 215, 422, 159, 486},
        {142, 277, 328, 84, 75, 181, 59, 334, 0, 266, 172, 214, 198, 291, 164, 99, 354, 365},
        {350, 483, 152, 213, 338, 303, 269, 148, 266, 0, 438, 383, 197, 167, 226, 330, 87, 531},
        {155, 204, 479, 234, 100, 249, 178, 488, 172, 438, 0, 216, 348, 434, 283, 160, 526, 312},
        {76, 105, 352, 189, 180, 83, 159, 363, 214, 383, 216, 0, 400, 298, 158, 291, 462, 156},
        {336, 475, 335, 216, 267, 344, 242, 335, 198, 197, 348, 400, 0, 329, 288, 196, 260, 556},
        {300, 403, 54, 208, 339, 217, 258, 66, 291, 167, 434, 298, 329, 0, 154, 384, 207, 420},
        {146, 262, 204, 84, 193, 76, 115, 215, 164, 226, 283, 158, 288, 154, 0, 263, 304, 304},
        {215, 330, 416, 182, 113, 275, 155, 422, 99, 330, 160, 291, 196, 384, 263, 0, 413, 430},
        {435, 564, 169, 300, 426, 380, 356, 159, 354, 87, 526, 462, 260, 207, 304, 413, 0, 605},
        {222, 108, 474, 345, 317, 228, 313, 486, 365, 531, 312, 156, 556, 420, 304, 430, 605, 0},
    };
    public Cities() {
    }
    public String getName(int cityIndex) {
        return names[cityIndex];
    }
    public double getDistance(int cityI, int cityJ) {
        return distances[cityI][cityJ];
    }
}

