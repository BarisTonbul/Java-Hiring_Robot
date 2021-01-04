# This is a hiring robot coded with Java language.
## The main purpose of this program is to provide companies with a lot of job applications to perform a quicker comparison among applicants who will be invited to meet face-to-face based on old candidate data.

- I imported various informations from previous candidates.
- I store 4 attribute and one accepted or rejected data. These can be like that communication,skill,appereance,experience,acceptep_or_rejected as 0,1
- These attributes has points up to 5.
- I take average of that attributes and put them into the X Y graphic. Each applicant is shown on the chart as a single point. Every attribute has same weight for now while taking average.
- After that operation, when we put new candidate with 4 attribute program calculates the euclidean distance to the nearest 5 previous candidates.
- If 3 of them was accepted before according to the old data, program prints "Accepted" else "Rejected".

## to try with different candidates use the "yeni_adaylar" array in the main method.


