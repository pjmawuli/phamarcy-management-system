package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.entities.DashboardEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DashboardFunctions {

    /**
     * @return - DashoardEntity : a summary of all dashboard statistics in the pharmacy
     * @throws SQLException
     */
    public static DashboardEntity getDashboardSummary() throws SQLException, ExecutionException, InterruptedException {

        // create callable interfaces
        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(() -> DrugFunctions.getAllDrugsCount());
        tasks.add(() -> SupplierFunctions.getAllSuppliersCount());
        tasks.add(() -> PurchaseFunctions.getAllPurchasesCount());
        tasks.add(() -> DrugFunctions.getAllLowInStockDrugsCount());
        tasks.add(() -> PurchaseFunctions.getAllTodayPurchasesCount());


        //        use `try with resources` to automatically release the resources when done
        try
                (
                        ExecutorService executor = Executors.newFixedThreadPool(2);
                ) {

            // submit all tasks to executor
            List<Future<Integer>> results = executor.invokeAll(tasks);

            List<Integer> data = new ArrayList<>();

            // process the results
            for (Future<Integer> result : results) {
                data.add(result.get());
            }

            return new DashboardEntity(
                    data.get(0),
                    data.get(1),
                    data.get(2),
                    data.get(3),
                    data.get(4)
            );

        } catch (Exception e) {
            System.out.println("Could not retrieve dashboard statistics");
            e.printStackTrace();
            throw e;
        }
    }
}
