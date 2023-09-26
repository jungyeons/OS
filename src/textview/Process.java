package textview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Process {

    public int PC;
    public String name;
    public List<Instruction> instructions;

    public Process(String name) {
        this.PC = 0;
        this.name = name;
        this.instructions = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/" + name));
            while (scanner.hasNextLine()) this.instructions.add(Instruction.valueOf(scanner.nextLine()));
        } catch (FileNotFoundException e) {
            System.err.println("File not found : " + name);
        }
    }

}
