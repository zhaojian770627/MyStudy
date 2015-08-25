package algorithm.linear;

public class ParentStack {
        String[] stack = new String[51];
        ChildStack[] cs = new ChildStack[5];
        int oldTop[] = new int[5];
        int D[] = new int[5];
        int NewBase[] = new int[5];

        int sum = 0;
        int inc = 0;

        public ParentStack() {
                cs[0] = new ChildStack(stack, 0, 10);
                cs[1] = new ChildStack(stack, 10, 10);
                cs[2] = new ChildStack(stack, 20, 10);
                cs[3] = new ChildStack(stack, 30, 10);
                cs[4] = new ChildStack(stack, 40, 10);
        }

        /**
         * @param args
         * @throws Exception
         */
        public static void main(String[] args) throws Exception {
                ParentStack ps = new ParentStack();
                ps.execute();
                ps.reAllocate();
                System.out.println("Ok");
                System.exit(0);
        }

        private void execute() throws Exception {
                cs[0].push("a");
                cs[0].push("b");
                cs[0].push("z");
                cs[0].push("x");
                cs[0].push("y");
                cs[0].push("o");
                
                cs[1].push("c");
                cs[1].push("d");
                cs[1].push("z");
                cs[1].push("x");

                cs[2].push("e");
                cs[2].push("f");
                cs[3].push("g");
                cs[3].push("h");
                cs[4].push("i");
                cs[4].push("j");
        }

        private void reAllocate() {
                sum = stack.length - 1;
                inc = 0;

                // 收集统计数字
                for (int i = 0; i < cs.length; i++) {
                        sum -= (cs[i].getTop() - cs[i].base);
                        if (cs[i].getTop() > oldTop[i]) {
                                D[i] = cs[i].getTop() - oldTop[i];
                                inc = inc + D[i];
                        } else
                                D[i] = 0;
                }

                // 计算分配因子
                double a = 0.1 * sum / (stack.length - 1);
                double b = 0.9 * sum / inc;

                NewBase[0] = cs[0].getBase();
                double aa = 0;

                for (int j = 1; j < cs.length; j++) {
                        double t = a + aa + D[j - 1] * b;
                        NewBase[j] = (int) (NewBase[j - 1] + cs[j - 1].getTop()
                                        - cs[j - 1].getBase() + Math.floor(t) - Math.floor(aa));
                        

                        System.out.println(String.valueOf(j) + ":" + "t:" + t + " aa:" + aa
                                        + " 基地址" + String.valueOf(NewBase[j]));
                        aa = t;
                }

                System.out.println("和:" + sum);
                System.out.println("递增:" + inc);
                System.out.println("分配因子:" + a);
                if (sum < 0)
                        return;

        }
}