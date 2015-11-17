import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Program entry point
    public static void main(String[] args) throws FileNotFoundException {
        System.out.printf("Type in a filename: ");

        Scanner fnsc = new Scanner(System.in);
        Scanner sc = new Scanner(new File(fnsc.nextLine()));

        List<String> init_list = new ArrayList<>();

        System.out.println("\nReading data...");
        double stTime = System.nanoTime();
        while (sc.hasNextLine()) {
            init_list.add(sc.nextLine());
        }
        double spTime = System.nanoTime();
        System.out.printf("Data read in %f sec\n\n", ((spTime - stTime) / 1000000000));

        // Mergesort
        System.out.print("Mergesort times (sec):\n");
        stats(new Merge(), init_list, 50);

        // Insertion sort
        System.out.print("Insertion sort times (sec):\n");
        stats(new Insertion(), init_list, 50);

        // Selection sort
        System.out.print("Selection sort times (sec):\n");
        stats(new Selection(), init_list, 50);

        // Bubble sort
        System.out.print("Bubblesort times (sec):\n");
        stats(new Bubble(), init_list, 50);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> void stats(Sort method, List<T> l, int iterations) {
        List<Double> times = new ArrayList<>();
        T[] dataset;

        for (int i=0;i<iterations;i++) {
            dataset = l.toArray((T[]) new Comparable[l.size()]);
            times.add(method.sort(dataset));
        }

        times.forEach(System.out::println);
        System.out.printf("Average: %f\n", times.parallelStream()
                .mapToDouble(t -> t)
                .average()
                .getAsDouble());
        System.out.printf("Min: %f\n", times.parallelStream()
                .mapToDouble(t -> t)
                .min()
                .getAsDouble());
        System.out.printf("Max: %f\n", times.parallelStream()
                .mapToDouble(t -> t)
                .max()
                .getAsDouble());
        System.out.printf("Total: %f\n\n", times.parallelStream()
                .mapToDouble(t -> t)
                .sum());
        times.clear();
    }
}
