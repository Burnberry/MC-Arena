package noppe.minecraft.arena.spellcasting;

import noppe.minecraft.arena.helpers.M;
import org.apache.commons.math3.linear.*;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S {
    // Feel free to use these functions but be warned if you want to edit them
    // Only the solved section of math is present here
    // If you have questions you can contact @Noppe on discord

    public static Vector unitY = new Vector(0, 1, 0);

    public static double similarityError(List<Vector> P, List<Vector> Q0){
        // todo explain metric
        List<Vector> Q = new ArrayList<>();
        for (Vector v: Q0){
            Q.add(v.clone());
        }
        List<Double> variables = S.optimize(P, Q);
//        M.print(variables.toString());
        double a = variables.get(0);
        double dx = variables.get(1);
        double dy = variables.get(2);

        // if a < 0 discard
        // if a == 0 discard as well?
        if (a <= 0){
            return 1;
        }
        S.transform(Q, a, dx, dy);
//        M.print(Q.toString());
        return S.integrateTrue(P, Q);
    }

    public static double similarityError(Spell P, List<Vector> Q){
        return S.similarityError(P.points, Q);
    }

    public static double similarityError(Spell P, Spell Q){
        return S.similarityError(P.points, Q.points);
    }

    public static List<Double> optimize(List<Vector> P, List<Vector> Q){
        // Gets scale and translation for Q to minimize similarity error with P
        // returns a, dx, dy
        // solved math: see @Noppe

        // first get variable coefficients
        double A2, A, DX2, DX, DY2, DY, ADX, ADY;
        A2 = A = DX2 = DX = DY2 = DY = ADX = ADY = 0;

        for (int i=0; i<P.size()-1; i++){
//            M.print("appel");
            Vector P1, P2, Q1, Q2;
            P1 = P.get(i);
            P2 = P.get(i+1);
            Q1 = Q.get(i);
            Q2 = Q.get(i+1);
            double l = P1.distance(P2);

            List<Double> variablesX, variablesY;
            variablesX = S.getVariableTermValues(P1.getX(), P2.getX(), Q1.getX(), Q2.getX());
            variablesY = S.getVariableTermValues(P1.getY(), P2.getY(), Q1.getY(), Q2.getY());

            A2 += l*(variablesX.get(0) + variablesY.get(0));
            DX2 += l*variablesX.get(1);
            DY2 += l*variablesY.get(1);
            ADX += l*variablesX.get(2);
            ADY += l*variablesY.get(2);
            A += l*(variablesX.get(3) + variablesY.get(3));
            DX += l*variablesX.get(4);
            DY += l*variablesY.get(4);
        }
//        M.print("A2: "+A2);
//        M.print("A: "+A);
//        M.print("DX2: "+DX2);
//        M.print("DX: "+DX);
//        M.print("DY2: "+DY2);
//        M.print("DY: "+DY);
//        M.print("ADX: "+ADX);
//        M.print("ADY: "+ADY);


        // solve for partial derivatives = 0
        double[][] equations = {
                {2*A2, ADX, ADY},
                {ADX, 2*DX2, 0},
                {ADY, 0, 2*DY2}
        };
        double[] constants = {-A, -DX, -DY};

        // Create RealMatrix and RealVector instances
        RealMatrix matrix = MatrixUtils.createRealMatrix(equations);
        RealVector vector = MatrixUtils.createRealVector(constants);

        // Perform LU decomposition and solve
        DecompositionSolver solver = new LUDecomposition(matrix).getSolver();
        RealVector solution = solver.solve(vector);
        double a = solution.getEntry(0);
        double dx = solution.getEntry(1);
        double dy = solution.getEntry(2);
//        M.print("a: "+a);
//        M.print("dx: "+dx);
//        M.print("dy: "+dy);

        return Arrays.asList(a, -dx, -dy);
    }

    private static List<Double> getVariableTermValues(double x1, double x2, double y1, double y2){
        // returns variable values of optimisation integral
        // returns constants of these variables: c1*a² + c2*d² + c3*a*d + c4*a + c5*d
        // solved integral, see @Noppe
        double a2 = y1*y1 + y1*y2 + y2*y2;
        double d2 = 3;
        double ad = -3*(y1 + y2);
        double a = -x1*(2*y1 + y2) - x2*(2*y2 + y1);
        double d = 3*(x1 + x2);

        return Arrays.asList(a2, d2, ad, a, d);
    }

    public static void transform(List<Vector> P, double a, double dx, double dy){
        // scale and translate a list of vectors
        for (Vector p: P){
            p.setX(a*p.getX() + dx);
            p.setY(a*p.getY() + dy);
        }
    }

    public static double integrateTrue(List<Vector> P, List<Vector> Q){
        // error metric for spell similarity without optimisation

        double E = 0;
        double L = 0;
        for (int i=0; i<P.size()-1; i++){
//            M.print("peer");
            Vector P1, P2, Q1, Q2;
            P1 = P.get(i);
            P2 = P.get(i+1);
            Q1 = Q.get(i);
            Q2 = Q.get(i+1);

            double e = 0;
            double l = P1.distance(P2);
            e += S.integrateSection(P1.getX(), P2.getX(), Q1.getX(), Q2.getX());
            e += S.integrateSection(P1.getY(), P2.getY(), Q1.getY(), Q2.getY());
            e *= l;

            E += e;
            L += l; // section's error is weighted by its size
        }
        // normalize error for size
        // Error scales quadratically with length -> /L²
        // Each section's error needs to be weighted as follows l/L
        // only l is used in the loop so do /L here
        // total normalization here -> /L³
        E /= L*L*L;
//        M.print("total error"+E);
        return E;
    }

    private static double integrateSection(double x1, double x2, double y1, double y2){
        // Solved integral see @Noppe
        double A = y1-x1;
        double B = y2-x2;
        double error = A*A + A*B + B*B;
//        M.print(""+ x1);
//        M.print(""+ x2);
//        M.print(""+ y1);
//        M.print(""+ y2);
//        M.print("Section Error"+error);
        return error;
    }

    public static void projectPointsXY(List<Vector> points, Vector unitX){
        for (Vector point: points){
            double dot = unitX.dot(point);
            point.setX(dot);
            point.setZ(0);
        }
    }

//    public static void TestSpells(){
//        double a = -0.5;
//        double b = 0.2;
//        double c = 0.5;
//        Spell Vert = new Spell(Arrays.asList(new Vector(a+3, 8, 0), new Vector(b+3, 8, 0), new Vector(c+3, 8, 0)));
//        Spell Hor = new Spell(Arrays.asList(new Vector(a, 0, 0), new Vector(b, 0, 0), new Vector(c, 0, 0)));
////        double e = S.integrateTrue(Hor.points, Vert.points);
////        M.print(""+e);
//        double e = S.similarityError(Hor, Vert);
//        M.print(""+e);
//    }
}
