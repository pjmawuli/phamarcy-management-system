package com.lambda.pharmacymangementsystem.controller;

/**
 * Manages the instances of controllers within the application, ensuring that there is only one instance of each controller.
 * This class follows the Singleton design pattern to provide a global point of access to the {@link DrugController} instance.
 */
public class ControllerManager {
    // The single instance of ControllerManager for Singleton pattern
    private static ControllerManager instance;
    // Reference to the DrugController instance
    private DrugController drugController;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ControllerManager() {}

    /**
     * Provides the global point of access to the ControllerManager instance.
     * If the instance does not exist, it is created.
     *
     * @return The single instance of ControllerManager.
     */
    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    /**
     * Retrieves the current instance of {@link DrugController}.
     *
     * @return The current instance of DrugController.
     */
    public DrugController getDrugController() {
        return drugController;
    }

    /**
     * Sets the instance of {@link DrugController}.
     * This method allows changing the reference to the DrugController instance.
     *
     * @param drugController The DrugController instance to set.
     */
    public void setDrugController(DrugController drugController) {
        this.drugController = drugController;
    }
}