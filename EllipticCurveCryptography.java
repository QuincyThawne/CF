import java.util.*;

class ECC {
    int a, b, p; // Elliptic curve parameters
    int gx = -1, gy = -1; // Generator point
    int privateKey = -1;

    public void setCurve(int a, int b, int p) {
        this.a = a;
        this.b = b;
        this.p = p;
        System.out.println("Elliptic curve: y^2 = x^3 + " + a + "x + " + b + " mod " + p);
    }

    public void setPrivateKey(int privateKey) {
        this.privateKey = privateKey;
    }

    public void setGeneratorPoint(int gx, int gy) {
        this.gx = gx;
        this.gy = gy;
        System.out.println("Generator point set: G(" + gx + ", " + gy + ")");
    }

    public int[] addPoints(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            return doublePoint(x1, y1);
        }

        int numerator = (y2 - y1 + p) % p;
        int denominator = (x2 - x1 + p) % p;
        int lambda = (numerator * modInverse(denominator)) % p;

        int x3 = (lambda * lambda - x1 - x2) % p;
        if (x3 < 0) x3 += p;

        int y3 = (lambda * (x1 - x3) - y1) % p;
        if (y3 < 0) y3 += p;

        return new int[]{x3, y3};
    }

    public int[] doublePoint(int x1, int y1) {
        if (y1 == 0) return new int[]{0, 0};

        int numerator = (3 * x1 * x1 + a) % p;
        int denominator = (2 * y1) % p;
        int lambda = (numerator * modInverse(denominator)) % p;

        int x3 = (lambda * lambda - 2 * x1) % p;
        if (x3 < 0) x3 += p;

        int y3 = (lambda * (x1 - x3) - y1) % p;
        if (y3 < 0) y3 += p;

        return new int[]{x3, y3};
    }

    public int[] scalarMultiply(int s, int x, int y) {
        int[] result = {x, y};
        int[] temp = {x, y};

        for (int i = 1; i < s; i++) {
            result = addPoints(result[0], result[1], temp[0], temp[1]);
        }
        return result;
    }

    public int[][] encrypt(int[] message, int[] publicKey, int k) {
        int[] C1 = scalarMultiply(k, gx, gy);
        int[] kP = scalarMultiply(k, publicKey[0], publicKey[1]);
        int[] C2 = addPoints(message[0], message[1], kP[0], kP[1]);
        return new int[][]{C1, C2};
    }

    public int[] decrypt(int[][] cipherText) {
        int[] C1 = cipherText[0];
        int[] C2 = cipherText[1];
        int[] sC1 = scalarMultiply(privateKey, C1[0], C1[1]);
        return subtractPoints(C2[0], C2[1], sC1[0], sC1[1]);
    }

    public int[] subtractPoints(int x1, int y1, int x2, int y2) {
        return addPoints(x1, y1, x2, (p - y2) % p);
    }

    public int modInverse(int a) {
        a = a % p;
        for (int x = 1; x < p; x++) {
            if ((a * x) % p == 1) return x;
        }
        return 1;
    }
}

public class EllipticCurveCryptography {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ECC ecc = new ECC();

        while (true) {
            System.out.println("\n===== Elliptic Curve Cryptography Menu =====");
            System.out.println("1. Select Elliptic Curve");
            System.out.println("2. Generate Generator Point");
            System.out.println("3. Generate Public Key");
            System.out.println("4. Encrypt Message");
            System.out.println("5. Decrypt Message");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter values a, b, p: ");
                    int a = sc.nextInt(), b = sc.nextInt(), p = sc.nextInt();
                    ecc.setCurve(a, b, p);
                }

                case 2 -> {
                    System.out.println("Finding generator points...");
                    ArrayList<int[]> points = new ArrayList<>();
                    for (int x = 0; x < ecc.p; x++) {
                        int rhs = (x * x * x + ecc.a * x + ecc.b) % ecc.p;
                        for (int y = 0; y < ecc.p; y++) {
                            if ((y * y) % ecc.p == rhs) {
                                points.add(new int[]{x, y});
                                System.out.println("Point: (" + x + ", " + y + ")");
                            }
                        }
                    }
                    System.out.print("Select generator index: ");
                    int index = sc.nextInt();
                    if (index >= 0 && index < points.size()) {
                        ecc.setGeneratorPoint(points.get(index)[0], points.get(index)[1]);
                    }
                }

                case 3 -> {
                    System.out.print("Enter private key: ");
                    int s = sc.nextInt();
                    ecc.setPrivateKey(s);
                    int[] publicKey = ecc.scalarMultiply(s, ecc.gx, ecc.gy);
                    System.out.println("Public Key: (" + publicKey[0] + ", " + publicKey[1] + ")");
                }

                case 4 -> {
                    System.out.print("Enter message (Mx, My): ");
                    int mx = sc.nextInt(), my = sc.nextInt();
                    System.out.print("Enter secret key: ");
                    int k = sc.nextInt();
                    int[] publicKey = ecc.scalarMultiply(ecc.privateKey, ecc.gx, ecc.gy);
                    int[][] encrypted = ecc.encrypt(new int[]{mx, my}, publicKey, k);
                    System.out.println("Ciphertext C1: (" + encrypted[0][0] + ", " + encrypted[0][1] + ")");
                    System.out.println("Ciphertext C2: (" + encrypted[1][0] + ", " + encrypted[1][1] + ")");
                }

                case 5 -> {
                    System.out.print("Enter Ciphertext (C1x, C1y, C2x, C2y): ");
                    int[][] cipherText = {
                        {sc.nextInt(), sc.nextInt()},
                        {sc.nextInt(), sc.nextInt()}
                    };
                    int[] decrypted = ecc.decrypt(cipherText);
                    System.out.println("Decrypted Message: (" + decrypted[0] + ", " + decrypted[1] + ")");
                }

                case 6 -> {
                    sc.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
