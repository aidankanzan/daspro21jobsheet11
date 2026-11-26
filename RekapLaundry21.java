import java.util.Scanner;

public class RekapLaundry21 {
    static Scanner sc = new Scanner(System.in);

    static void inputData(int[][] data, String[] layanan, String[] hari) {
        System.out.println("=== INPUT DATA BERAT CUCIAN (kg) ===");
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("Layanan: " + layanan[i]);
            for (int j = 0; j < hari.length; j++) {
                System.out.print("  Hari " + hari[j] + ": ");
                data[i][j] = sc.nextInt();
            }
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga, String[] hari) {
        System.out.println("\n=== TABEL REKAP BERAT CUCIAN (kg) ===");
        System.out.printf("%-15s %-12s", "Layanan", "Harga/kg");
        for (String h : hari) {
            System.out.printf("%-8s", h);
        }
        System.out.println();

        for (int i = 0; i < layanan.length; i++) {
            System.out.printf("%-15s Rp%-10d", layanan[i], harga[i]);
            for (int j = 0; j < hari.length; j++) {
                System.out.printf("%-8d", data[i][j]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;
        for (int j = 0; j < data[idxLayanan].length; j++) {
            total += data[idxLayanan][j];
        }
        return total;
    }

    static void tampilLayananTertinggi(int[][] data, String[] layanan) {
        int idxMax = 0;
        int totalMax = totalPerLayanan(data, 0);

        for (int i = 1; i < layanan.length; i++) {
            int totalSekarang = totalPerLayanan(data, i);
            if (totalSekarang > totalMax) {
                totalMax = totalSekarang;
                idxMax = i;
            }
        }
        System.out.println("\nLayanan dengan total berat tertinggi: " + layanan[idxMax] + " (" + totalMax + " kg)");
    }

    static int hariTersibuk(int[][] data) {
        int jumlahHari = data[0].length;
        int idxHariMax = 0;
        int beratHariMax = 0;

        for (int i = 0; i < data.length; i++) {
            beratHariMax += data[i][0];
        }

        for (int j = 1; j < jumlahHari; j++) {
            int totalHari = 0;
            for (int i = 0; i < data.length; i++) {
                totalHari += data[i][j];
            }
            if (totalHari > beratHariMax) {
                beratHariMax = totalHari;
                idxHariMax = j;
            }
        }
        return idxHariMax;
    }

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga) {
        System.out.println("\n=== PENDAPATAN LAUNDRY (6 HARI) ===");
        long totalSemuaPendapatan = 0;

        for (int i = 0; i < layanan.length; i++) {
            int totalKg = totalPerLayanan(data, i);
            long pendapatanLayanan = (long) totalKg * harga[i];
            totalSemuaPendapatan += pendapatanLayanan;
            System.out.printf("%-15s : %3d kg x Rp%-6d = Rp%d\n", layanan[i], totalKg, harga[i], pendapatanLayanan);
        }
        System.out.println("Total Pendapatan Selama 6 Hari : Rp" + totalSemuaPendapatan);
    }

    public static void main(String[] args) {
        String[] layanan = {"Cuci Kering", "Cuci Setrika", "Setrika Saja", "Express"};
        int[] harga = {6000, 8000, 5000, 12000};
        String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        int[][] data = new int[layanan.length][hari.length];

        inputData(data, layanan, hari);
        tampilTabel(data, layanan, harga, hari);

        System.out.println("\n=== TOTAL BERAT PER LAYANAN ===");
        for (int i = 0; i < layanan.length; i++) {
            System.out.println(layanan[i] + " : " + totalPerLayanan(data, i) + " kg");
        }

        tampilLayananTertinggi(data, layanan);

        int idxSibuk = hariTersibuk(data);
        System.out.println("Hari tersibuk                : " + hari[idxSibuk]);

        tampilPendapatan(data, layanan, harga);

        sc.close();
    }
}