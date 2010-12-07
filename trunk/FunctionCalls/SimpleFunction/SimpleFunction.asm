(SimpleFunction.test)
@0
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@0
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@LCL
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

@LCL
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

@SP
A=M
A=A-1
D=M
A=A-1
M=D+M
D=A+1
@SP
M=D

@SP
A=M
A=A-1
M=!M

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

@SP
A=M
A=A-1
D=M
A=A-1
M=D+M
D=A+1
@SP
M=D

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

