public class binarysearch {
    static int buscar (int [] a, int x, int ini, int fin) {
        if (ini > fin) return -1;
        int mid = (ini + fin) >>> 1;
        if (a[mid] == x) return mid;
        if (x < a[mid]) return buscar (a, x, ini, mid - 1);
        return buscar (a, x, mid + 1, fin);
    }
}
