import java.util.Scanner;

public class RekapLaundry21 {
    static Scanner sc = new Scanner(System.in);

    static void inputData(int[][] data, String[] layanan, int jumlahHari) {
        System.out.println("\n=== INPUT DATA BERAT CUCIAN (kg) ===");
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("Layanan: " + layanan[i]);
            for (int j = 0; j < jumlahHari; j++) {
                System.out.print("  Hari ke-" + (j + 1) + ": ");
                data[i][j] = sc.nextInt();
            }
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga, int jumlahHari) {
        System.out.println("\n=== TABEL REKAP BERAT CUCIAN (kg) ===");
        System.out.printf("%-18s %-12s", "Layanan", "Harga/kg");
        for (int j = 0; j < jumlahHari; j++) {
            System.out.printf("%-10s", "Hari ke-" + (j + 1));
        }
        System.out.println();

        for (int i = 0; i < layanan.length; i++) {
            System.out.printf("%-18s Rp%-10d", layanan[i], harga[i]);
            for (int j = 0; j < jumlahHari; j++) {
                System.out.printf("%-10d", data[i][j]);
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

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga, int jumlahHari) {
        System.out.println("\n=== PENDAPATAN LAUNDRY (" + jumlahHari + " HARI) ===");
        long totalSemuaPendapatan = 0;

        for (int i = 0; i < layanan.length; i++) {
            int totalKg = totalPerLayanan(data, i);
            long pendapatanLayanan = (long) totalKg * harga[i];
            totalSemuaPendapatan += pendapatanLayanan;
            System.out.printf("%-18s : %3d kg x Rp%-6d = Rp%d\n", layanan[i], totalKg, harga[i], pendapatanLayanan);
        }
        System.out.println("Total Pendapatan Selama " + jumlahHari + " Hari : Rp" + totalSemuaPendapatan);
    }

    public static void main(String[] args) {
        System.out.print("Masukkan jumlah layanan: ");
        int jumlahLayanan = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Masukkan jumlah hari: ");
        int jumlahHari = sc.nextInt();
        sc.nextLine(); 

        String[] layanan = new String[jumlahLayanan];
        int[] harga = new int[jumlahLayanan];
        int[][] data = new int[jumlahLayanan][jumlahHari];

        System.out.println("\n=== INPUT NAMA & HARGA LAYANAN ===");
        for (int i = 0; i < jumlahLayanan; i++) {
            System.out.print("Nama layanan ke-" + (i + 1) + ": ");
            layanan[i] = sc.nextLine();
            System.out.print("Harga per kg untuk " + layanan[i] + ": ");
            harga[i] = sc.nextInt();
            sc.nextLine();
        }

        inputData(data, layanan, jumlahHari);
        tampilTabel(data, layanan, harga, jumlahHari);

        System.out.println("\n=== TOTAL BERAT PER LAYANAN ===");
        for (int i = 0; i < jumlahLayanan; i++) {
            System.out.println(layanan[i] + " : " + totalPerLayanan(data, i) + " kg");
        }

        tampilLayananTertinggi(data, layanan);

        int idxSibuk = hariTersibuk(data);
        System.out.println("Hari tersibuk                : Hari ke-" + (idxSibuk + 1));

        tampilPendapatan(data, layanan, harga, jumlahHari);

        sc.close();
    }
}