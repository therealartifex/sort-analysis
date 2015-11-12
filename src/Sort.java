abstract class Sort {
    protected double stTime; // Start
    protected double spTime; // Stop

    // Returns the time it took to sort the array
    public abstract double sort(Comparable[] a);

}

@SuppressWarnings("unchecked")
class Merge extends Sort {
    public double sort(Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        stTime = System.nanoTime();
        mergeSort(a, tmp,  0,  a.length - 1);
        spTime = System.nanoTime();
        return (spTime - stTime) / 1000000000;
    }

    private static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right) {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    private static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
}

@SuppressWarnings("unchecked")
class Insertion extends Sort {
    public double sort(Comparable[] a) {
        stTime = System.nanoTime();
        for (int i = 1; i < a.length; i++) {
            Comparable t = a[i];
            int j;
            for (j = i - 1; j >= 0 && t.compareTo(a[j]) < 0; j--)
                a[j+1] = a[j];
            a[j+1] = t;
        }
        spTime = System.nanoTime();
        return (spTime - stTime) / 1000000000;
    }
}

@SuppressWarnings("unchecked")
class Bubble extends Sort {
    public double sort(Comparable[] a) {
        int right_border = a.length - 1;
        stTime = System.nanoTime();
        do {
            int last_exchange = 0;
            for (int i = 0; i < right_border; i++) {
                if (a[i].compareTo(a[i + 1]) > 0)  {
                    Comparable temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    last_exchange = i;
                }
            }
            right_border = last_exchange;
        }
        while ( right_border > 0 );
        spTime = System.nanoTime();
        return (spTime - stTime) / 1000000000;
    }
}

@SuppressWarnings("unchecked")
class Selection extends Sort {
    public double sort(Comparable[] a) {
        int min;
        Comparable temp;
        stTime = System.nanoTime();

        for (int i=0; i<a.length-1; i++) {
            min = i; //index of object with minimum value

            for (int scan = i+1; scan < a.length; scan++)
                if (a[scan].compareTo(a[min]) < 0)
                    min = scan;

            //swap value
            temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        spTime = System.nanoTime();
        return (spTime - stTime) / 1000000000;
    }
}