@256
D=A
@SP
M=D
@RETURN0
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D
@LCL
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@ARG
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THIS
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THAT
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@5
D=A
@SP
A=M
D=A-D
@ARG
M=D
@SP
A=M
D=A
@LCL
M=D
@Sys.init
0;JMP
(RETURN0)

(Class1.set)
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@Class1.0
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

@ARG
D=M
@1
A=D+A
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@Class1.1
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

@0
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

// restore RET
@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R14
M=D

// pop ARG
@ARG
D=M
@0
A=D+A
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

// SP = ARG +1
@ARG
D=M+1
@SP
M=D
// restore ALL
@LCL
A=M
A=A-1
D=M
@R4
M=D

@LCL
A=M
A=A-1
A=A-1
D=M
@R3
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
D=M
@R2
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R1
M=D

// jump
@R14
A=M
0;JMP

(Class1.get)
@Class1.0
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@Class1.1
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@SP
A=M
A=A-1
D=M
A=A-1
M=M-D
D=A+1
@SP
M=D

// restore RET
@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R14
M=D

// pop ARG
@ARG
D=M
@0
A=D+A
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

// SP = ARG +1
@ARG
D=M+1
@SP
M=D
// restore ALL
@LCL
A=M
A=A-1
D=M
@R4
M=D

@LCL
A=M
A=A-1
A=A-1
D=M
@R3
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
D=M
@R2
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R1
M=D

// jump
@R14
A=M
0;JMP

(Class2.set)
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@Class2.0
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

@ARG
D=M
@1
A=D+A
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@Class2.1
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

@0
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

// restore RET
@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R14
M=D

// pop ARG
@ARG
D=M
@0
A=D+A
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

// SP = ARG +1
@ARG
D=M+1
@SP
M=D
// restore ALL
@LCL
A=M
A=A-1
D=M
@R4
M=D

@LCL
A=M
A=A-1
A=A-1
D=M
@R3
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
D=M
@R2
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R1
M=D

// jump
@R14
A=M
0;JMP

(Class2.get)
@Class2.0
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@Class2.1
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@SP
A=M
A=A-1
D=M
A=A-1
M=M-D
D=A+1
@SP
M=D

// restore RET
@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R14
M=D

// pop ARG
@ARG
D=M
@0
A=D+A
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

// SP = ARG +1
@ARG
D=M+1
@SP
M=D
// restore ALL
@LCL
A=M
A=A-1
D=M
@R4
M=D

@LCL
A=M
A=A-1
A=A-1
D=M
@R3
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
D=M
@R2
M=D

@LCL
A=M
A=A-1
A=A-1
A=A-1
A=A-1
D=M
@R1
M=D

// jump
@R14
A=M
0;JMP

(Sys.init)
@6
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@8
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@RETURN1
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D
@LCL
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@ARG
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THIS
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THAT
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@7
D=A
@SP
A=M
D=A-D
@ARG
M=D
@SP
A=M
D=A
@LCL
M=D
@Class1.set
0;JMP
(RETURN1)

@R5
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

@23
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@15
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@RETURN2
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D
@LCL
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@ARG
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THIS
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THAT
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@7
D=A
@SP
A=M
D=A-D
@ARG
M=D
@SP
A=M
D=A
@LCL
M=D
@Class2.set
0;JMP
(RETURN2)

@R5
D=A
@R13
M=D
@SP
A=M
A=A-1
D=M
@R13
A=M
M=D
@SP
M=M-1

@RETURN3
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D
@LCL
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@ARG
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THIS
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THAT
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@5
D=A
@SP
A=M
D=A-D
@ARG
M=D
@SP
A=M
D=A
@LCL
M=D
@Class1.get
0;JMP
(RETURN3)

@RETURN4
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D
@LCL
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@ARG
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THIS
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@THAT
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D
@5
D=A
@SP
A=M
D=A-D
@ARG
M=D
@SP
A=M
D=A
@LCL
M=D
@Class2.get
0;JMP
(RETURN4)

(Sys.init$WHILE)
@Sys.init$WHILE
0;JMP

