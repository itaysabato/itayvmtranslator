@111
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@333
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@888
D=A
@SP
A=M
M=D
D=A+1
@SP
M=D

@StaticTest.asm.8
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

@StaticTest.asm.3
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

@StaticTest.asm.1
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

@StaticTest.asm.3
D=M
@SP
A=M
M=D
D=A+1
@SP
M=D

@StaticTest.asm.1
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

@StaticTest.asm.8
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

