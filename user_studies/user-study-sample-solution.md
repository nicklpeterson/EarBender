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