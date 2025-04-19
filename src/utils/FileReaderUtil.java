package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static <T> List<T> readFile(String path, Class<T> clazz, Class<?>... parameterTypes) {
        List<T> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            // Bỏ qua dòng đầu tiên (header)
            br.readLine();

            Constructor<T> constructor = clazz.getConstructor(parameterTypes);

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+");

                Object[] args = new Object[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    if (parameterTypes[i] == int.class || parameterTypes[i] == Integer.class) {
                        args[i] = Integer.parseInt(parts[i]);
                    } else { // mặc định là String
                        args[i] = parts[i];
                    }
                }
                T obj = constructor.newInstance(args);
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
