import java.util.Scanner;

public class BankSampah21 {
    static Scanner sc = new Scanner(System.in);

    static double[] inputSetoran(int n) {
        double[] setoran = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan berat setoran nasabah ke-" + (i + 1) + " (kg): ");
            setoran[i] = sc.nextDouble();
        }
        return setoran;
    }

    static void tampilSetoran(double[] setoran) {
        System.out.println("\n=== DAFTAR SETORAN SAMPAH ===");
        for (int i = 0; i < setoran.length; i++) {
            System.out.println("Nasabah ke-" + (i + 1) + " : " + setoran[i] + " kg");
        }
    }

    static double totalSetoran(double[] setoran) {
        double total = 0;
        for (double berat : setoran) {
            total += berat;
        }
        return total;
    }

    static double hitungSaldo(double beratKg, double hargaPerKg) {
        return beratKg * hargaPerKg;
    }

    static int nasabahTerbanyak(double[] setoran) {
        int idxTerbesar = 0;
        for (int i = 1; i < setoran.length; i++) {
            if (setoran[i] > setoran[idxTerbesar]) {
                idxTerbesar = i;
            }
        }
        return idxTerbesar;
    }

    public static void main(String[] args) {
        double hargaPerKg = 3000.0;

        System.out.print("Masukkan jumlah nasabah (N): ");
        int n = sc.nextInt();

        double[] dataSetoran = inputSetoran(n);

        tampilSetoran(dataSetoran);

        double totalBerat = totalSetoran(dataSetoran);
        System.out.println("\nTotal berat setoran seluruh nasabah: " + totalBerat + " kg");

        System.out.println("\n=== SALDO TIAP NASABAH (Rp3.000/kg) ===");
        for (int i = 0; i < dataSetoran.length; i++) {
            double saldo = hitungSaldo(dataSetoran[i], hargaPerKg);
            System.out.printf("Nasabah ke-%d : Rp%.2f\n", (i + 1), saldo);
        }

        int idxMax = nasabahTerbanyak(dataSetoran);
        System.out.println("\nNasabah dengan setoran terbesar adalah Nasabah ke-" + (idxMax + 1)
                + " (" + dataSetoran[idxMax] + " kg)");

        sc.close();
    }
}