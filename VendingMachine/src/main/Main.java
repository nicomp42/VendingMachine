package main;

import java.util.ArrayList;

import product.PotatoChips;
import product.Pringles;
import product.Product;
import product.Skittles;
import vendingMachine.VendingMachine;

/**
 * Test the VendingMachine class.
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Product product = null;
		// Create a new vending machine with 5 dispensers and $40 in coins already the change-giving device
		VendingMachine vm = new VendingMachine(5, 100, 100, 100, 5);	// 5.00 + 10.00 + 25.00 = $40

		stockVendingMachine(vm);

		// First test. Customer enters $2.60 and tries to buy Potato Chips
		vm.acceptCustomerMoney(5, 6, 7);		// Customer inserts some money .25 + .60 + 1.75 = $2.60
		System.out.println(vm.toString());
		product = vm.acceptCustomerInput(1, 1);	// Customer presses row 1, column 1 (1-based numbers)
		System.out.println("Product dispensed = " + product.getSKU() + " for $" + product.getCost());
		System.out.println(vm.toString());

		// Second test. Customer enters $1.75 and tries to buy Skittles
		vm.acceptCustomerMoney(0, 0, 7);		// Customer inserts some money $1.75
		product = vm.acceptCustomerInput(1, 2);	// Customer presses row 1, column 2 (1-based numbers)
		System.out.println("Product dispensed = " + product.getSKU() + " for $" + product.getCost());
		System.out.println(vm.toString());
		
		// third test. Customer enters $1.00 and tries to buy Pringles. We know this is Pringles so we can call the getFlavor() method if we do a type cast
		vm.acceptCustomerMoney(0, 0, 4);		// Customer inserts some money $1.00
		product = vm.acceptCustomerInput(1, 3);	// Customer presses row 1, column 3 (1-based numbers)
		System.out.println("Product dispensed = " + product.getSKU() + " for $" + product.getCost() + " (" + ((Pringles)(product)).getFlavor() + ")");
		System.out.println(vm.toString());
		printContentsOfVendingMachine(vm);
	}
	/**
	 * Print what's currently in the machine
	 * @param vm The machine
	 */
	private static void printContentsOfVendingMachine(VendingMachine vm) {
		ArrayList<String> contents = vm.getContents();
		System.out.println("Contents of vending machine:" );
		for (String s : contents) {
			System.out.println(s);
		}
	}
	/**
	 * Stock the machine for testing
	 * @param vm The machine
	 * @throws Exception 
	 */
	private static void stockVendingMachine(VendingMachine vm) throws Exception {
		// Upper left dispenser
		vm.AddProduct(new PotatoChips("12345", .75), 1,  1);
		vm.AddProduct(new PotatoChips("12345", .75), 1,  1);
		vm.AddProduct(new PotatoChips("12345", .75), 1,  1);
		vm.AddProduct(new PotatoChips("12345", .75), 1,  1);
		
		// Top row, second from the left
		vm.AddProduct(new Skittles("33333", 1.75), 1,  2);
		vm.AddProduct(new Skittles("33333", 1.75), 1,  2);
		vm.AddProduct(new Skittles("33333", 1.75), 1,  2);
		vm.AddProduct(new Skittles("33333", 1.75), 1,  2);

		// Top row, third from the left
		vm.AddProduct(new Pringles("33333", 1.00, "Plain"), 1,  3);
		vm.AddProduct(new Pringles("33334", 1.10, "Bacon"), 1,  3);
		vm.AddProduct(new Pringles("33335", 1.20, "Onion"), 1,  3);
		vm.AddProduct(new Pringles("33336", 1.30, "Cheddar Cheese"), 1,  3);
	}
}
