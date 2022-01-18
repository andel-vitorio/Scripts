#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <sstream>

using std::stringstream;

LRESULT CALLBACK WinProc(HWND, UINT, WPARAM, LPARAM);

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
    HWND        hwnd;
    MSG         msg;
    WNDCLASS    wndclass;

    wndclass.style =            CS_HREDRAW | CS_VREDRAW;
    wndclass.lpfnWndProc =      WinProc;
    wndclass.cbClsExtra =       0;
    wndclass.cbWndExtra =       0;
    wndclass.hInstance =        hInstance;
    wndclass.hIcon =            LoadIcon(NULL, IDI_APPLICATION);
    wndclass.hCursor =          LoadCursor(NULL, IDC_ARROW);
    wndclass.hbrBackground =    (HBRUSH) GetStockObject(WHITE_BRUSH);
    wndclass.lpszMenuName =     NULL;
    wndclass.lpszClassName =    "Basic Window";

    if (!RegisterClass(&wndclass)) {
        MessageBox(
            NULL,
            "Erro na criação da janela!",
            "Aplicação",
            MB_ICONERROR
        );

        return 0;
    }
    
    hwnd = CreateWindow(
        "Basic Window",
        "Aplicação",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT,
        CW_USEDEFAULT,
        CW_USEDEFAULT,
        CW_USEDEFAULT,
        NULL,
        NULL,
        hInstance,
        NULL
    );

    ShowWindow(hwnd, nCmdShow);

    UpdateWindow(hwnd);

    while (GetMessage(&msg, NULL, 0, 0)) {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return int(msg.wParam);
}

LRESULT CALLBACK WinProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam) {
    HDC             hdc;
    PAINTSTRUCT     ps;
    RECT            rect;

    switch (message) {
        case WM_PAINT:
            hdc = BeginPaint(hwnd, &ps);

            GetClientRect(hwnd, &rect);
            
            DrawText(
                hdc, 
                "Minha Janela!!",
                -1,
                &rect,
                DT_SINGLELINE | DT_CENTER | DT_VCENTER
            );

            EndPaint(hwnd, &ps);
            return 0;
        
        case WM_DESTROY:
            PostQuitMessage(0);
            return 0;
        
        default:
            return DefWindowProc(hwnd, message, wParam, lParam);
    }
}