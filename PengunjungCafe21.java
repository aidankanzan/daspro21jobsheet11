public class PengunjungCafe21 {

    static int hitungTotalBelanja(int... harga) {
        int total = 0;
        for (int h : harga) {
            total += h;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Total 1 barang : Rp" + hitungTotalBelanja(15000));
        System.out.println("Total 3 barang : Rp" + hitungTotalBelanja(10000, 25000, 5000));
        System.out.println("Total 5 barang : Rp" + hitungTotalBelanja(5000, 12000, 8000, 20000, 15000));
    }
}
