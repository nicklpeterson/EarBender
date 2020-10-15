SAMPLE SOLUTION:

SET layer1 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140) <br/>
SET layer2 NOTES(C, B[w], C) INSTRUMENT(violin) TEMPO(160) <br/>
SET layer3 NOTES(A, B, C) INSTRUMENT(piano) TEMPO(100) <br/>
PLAY <br/>
LOOP 3 TIMES <br/>
START layer1, layer2 <br/>
END LOOP <br/>
START layer3 <br/>
STOP <br/>

------------------------------------------

SAMPLE SOLUTION 2.0:

SET VAR var1 NOTES(A, C[w], D) INSTRUMENT(PIANO) TEMPO(160)
SET VAR var2 NOTES(C, B[w], C) INSTRUMENT(violin) TEMPO(160)
SET VAR var3 NOTES(A, B, C) INSTRUMENT(piano) TEMPO(100)
SET LIST layer1(var1)
SET LIST layer2(var2)
SET LIST layer3(var3)
RHYTHM LAYER(B.BB...B.BB....B) LENGTH(2)

FUNCTION fun1 {
    LOOP 3 TIMES
    PLAY SIMUL layer1, layer2
    END LOOP
}

START
fun1
PLAY layer3
STOP
